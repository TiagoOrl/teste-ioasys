package com.assemblermaticstudio.ioasysclient.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assemblermaticstudio.ioasysclient.Auth
import com.assemblermaticstudio.ioasysclient.model.user.User
import com.assemblermaticstudio.ioasysclient.repository.ApiAccess
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch

class LoginViewModel(private val apiAccess: ApiAccess) : ViewModel() {

    private val _output = MutableLiveData<State>()
    val output: LiveData<State> = _output


    fun login(email: String, password: String) {

        viewModelScope.launch {

            _output.postValue(State.Loading)
            val responseUser = apiAccess.loginUser(email, password)

            if (responseUser == null){
                _output.postValue(State.Error("Erro ao realizar o login, verifique a conexão."))
                return@launch
            }

            // code range 200 -> 300
            if (responseUser.isSuccessful) {
                _output.postValue(State.Success(responseUser.body()))
                Auth.client = responseUser.headers().get("client")!!
                Auth.uid = responseUser.headers().get("uid")!!
                Auth.token = responseUser.headers().get("access-token")!!
            }

            if (responseUser.code() == 401) {
                _output.postValue(State.Error("Erro com as credenciais, tente novamente."))
            }


            if (responseUser.code() in 402..499) {
                _output.postValue(State.Error("Erro ao realizar o login, tente novamente."))
            }
        }
    }


    sealed class State {
        object Loading : State()
        data class Success(val dataObject: User?) : State()
//        data class SuccessQueryDB(val dataObject: List<GIF>) : State()
        data class Error(val msg: String) : State()
    }
}