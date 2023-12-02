package com.zszuev.paymentsapp.domain.usecases

import com.zszuev.paymentsapp.domain.entities.LoginResult
import com.zszuev.paymentsapp.domain.repository.PaymentsRepository

class LoginUseCase(private val repository: PaymentsRepository) {

    suspend operator fun invoke(login: String, password: String): LoginResult {
        return repository.login(login, password)
    }
}