package com.assemblermaticstudio.ioasysclient.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)
