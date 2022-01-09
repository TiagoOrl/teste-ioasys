package com.assemblermaticstudio.ioasysclient.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assemblermaticstudio.ioasysclient.model.enterprise.InObject
import com.assemblermaticstudio.ioasysclient.repository.ApiAccess
import kotlinx.coroutines.launch

class SearchViewModel(private val apiAccess: ApiAccess) : ViewModel() {

    private val _output = MutableLiveData<State>()
    val output: LiveData<State> = _output


    fun queryEnterprises(query: String) {
        viewModelScope.launch {
            _output.postValue(State.Loading)
            val responseObject = apiAccess.queryEnterprises(query)

            if (responseObject == null) {
                _output.postValue(State.Error("Problema ao realizar a busca"))
                return@launch
            }

            if (responseObject.isSuccessful) {
                _output.postValue(State.Success(responseObject.body()))
            }

            if (responseObject.code() == 401) {
                _output.postValue(State.NotAuth)
            }

            if (responseObject.code() in 402..499) {
                _output.postValue(State.Error("Problema ao realizar a busca"))
            }
        }
    }

    fun setIdleState() {
        _output.postValue(State.Idle)
    }

    sealed class State {
        object Idle: State()
        object Loading : State()
        object NotAuth : State()
        data class Success(val dataObject: InObject?) : State()
        data class Error(val msg: String) : State()
    }
}