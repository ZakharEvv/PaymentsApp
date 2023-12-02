package com.zszuev.paymentsapp.data.dto

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("success")
    val isSuccess: Boolean,
    @SerializedName("response")
    val response: TokenDTO

)

data class TokenDTO(
    @SerializedName("token")
    val token: String
)