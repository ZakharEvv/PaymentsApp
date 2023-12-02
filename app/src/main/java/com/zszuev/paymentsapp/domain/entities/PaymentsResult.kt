package com.zszuev.paymentsapp.domain.entities

sealed class PaymentsResult {
    data class Success(val payments: List<Payment>) : PaymentsResult()
    object Error : PaymentsResult()
    object NetworkError : PaymentsResult()
}
