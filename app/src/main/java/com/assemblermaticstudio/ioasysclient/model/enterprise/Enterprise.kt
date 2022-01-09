package com.assemblermaticstudio.ioasysclient.model.enterprise

import com.google.gson.annotations.SerializedName


data class Enterprise(
    @SerializedName("id")
    val id: Int,

    @SerializedName("enterprise_name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("email_enterprise")
    var email: String?  = null,

    @SerializedName("photo")
    val photo: String,

    @SerializedName("share_price")
    val share_price: Float,

    @SerializedName("city")
    val city: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("enterprise_type_name")
    val enterpriseType: EnterpriseType
)
