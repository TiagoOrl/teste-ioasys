package com.assemblermaticstudio.ioasysclient.model.user

import com.google.gson.annotations.SerializedName

data class Investor(

    @SerializedName("id")
    val id : Int,

    @SerializedName("investor_name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("city")
    val city: String
)