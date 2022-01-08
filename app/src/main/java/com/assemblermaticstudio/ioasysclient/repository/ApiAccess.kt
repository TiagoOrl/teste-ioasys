package com.assemblermaticstudio.ioasysclient.repository

import android.os.RemoteException
import android.util.Log
import androidx.core.util.Pair
import com.assemblermaticstudio.ioasysclient.Auth
import com.assemblermaticstudio.ioasysclient.model.Login
import com.assemblermaticstudio.ioasysclient.model.user.User
import com.assemblermaticstudio.ioasysclient.service.ApiConnection
import retrofit2.*
import java.lang.Exception

class ApiAccess(private val connection: ApiConnection) {

     suspend fun loginUser(email: String, password: String) : Response<User>? {

        try {
            return connection.login(Login(email, password)).awaitResponse()
        } catch (ex: Exception) {
            return  null
        }
    }

}

//testeapple@ioasys.com.br