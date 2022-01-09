package com.assemblermaticstudio.ioasysclient.service

import com.assemblermaticstudio.ioasysclient.model.Login
import com.assemblermaticstudio.ioasysclient.model.enterprise.InObject
import com.assemblermaticstudio.ioasysclient.model.user.User
import retrofit2.Call
import retrofit2.http.*

interface ApiConnection {
    @POST("users/auth/sign_in")
    fun login(@Body login: Login) : Call<User>

    @GET("enterprises")
    fun queryCompanies(
        @Header("access-token") token: String,
        @Header("client") client: String,
        @Header("uid") uid: String,
        @Query("name") name: String
    ) : Call<InObject>
}