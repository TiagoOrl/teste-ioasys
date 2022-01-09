package com.assemblermaticstudio.ioasysclient.repository

import com.assemblermaticstudio.ioasysclient.Auth
import com.assemblermaticstudio.ioasysclient.model.Login
import com.assemblermaticstudio.ioasysclient.model.enterprise.InObject
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

    suspend fun queryEnterprises(query: String) : Response<InObject>? {
        try {
            return connection.queryCompanies(Auth.token, Auth.client, Auth.uid, query).awaitResponse()
        } catch(ex: Exception) {
            return  null
        }
    }
}

// testeapple@ioasys.com.br