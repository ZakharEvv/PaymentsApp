package com.zszuev.paymentsapp.domain.repository

import com.zszuev.paymentsapp.domain.entities.LoginResult
import com.zszuev.paymentsapp.domain.entities.Payment
import com.zszuev.paymentsapp.domain.entities.PaymentsResult

interface PaymentsRepository {

    fun checkIsLogged(): Boolean

    suspend fun login(login: String, password: String): LoginResult

    fun logout()

    suspend fun getPayments(): PaymentsResult
}