package com.assemblermaticstudio.ioasysclient.service

import com.assemblermaticstudio.ioasysclient.model.Login
import com.assemblermaticstudio.ioasysclient.model.user.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiConnection {
    @POST("users/auth/sign_in")
    fun login(@Body login: Login) : Call<User>
}