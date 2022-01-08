package com.assemblermaticstudio.ioasysclient.model.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("investor")
    val investor: Investor,

    @SerializedName("success")
    val success: Boolean
)
