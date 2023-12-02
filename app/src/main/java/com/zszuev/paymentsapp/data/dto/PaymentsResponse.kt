package com.zszuev.paymentsapp.data.dto

import com.google.gson.annotations.SerializedName

data class PaymentsResponse(
    @SerializedName("success")
    val isSuccess: Boolean,
    @SerializedName("response")
    val payments: List<PaymentDTO>
)

data class PaymentDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("amount")
    var amount: String,
    @SerializedName("created")
    val created: Int
)
