package com.zszuev.paymentsapp.domain.entities

sealed class LoginResult {
    object Success : LoginResult()
    object InvalidCredentials : LoginResult()
    object NetworkError : LoginResult()
}
