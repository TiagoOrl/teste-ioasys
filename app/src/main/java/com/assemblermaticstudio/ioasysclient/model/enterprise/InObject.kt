package com.assemblermaticstudio.ioasysclient.model.enterprise

import com.google.gson.annotations.SerializedName

data class InObject(
    @SerializedName("enterprises")
    val enterpriseList: List<Enterprise>
)
