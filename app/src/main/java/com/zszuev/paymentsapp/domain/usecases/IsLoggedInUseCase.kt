package com.zszuev.paymentsapp.domain.usecases

import com.zszuev.paymentsapp.domain.repository.PaymentsRepository

class IsLoggedInUseCase(private val repository: PaymentsRepository) {

    operator fun invoke() = repository.checkIsLogged()
}