package com.assemblermaticstudio.ioasysclient.model.enterprise

import com.google.gson.annotations.SerializedName

data class EnterpriseType(
    @SerializedName("id")
    val id: Int,

    @SerializedName("enterprise_type_name")
    val typeName: String
)
