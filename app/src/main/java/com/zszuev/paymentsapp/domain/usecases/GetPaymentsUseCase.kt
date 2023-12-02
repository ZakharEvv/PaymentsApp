package com.zszuev.paymentsapp.domain.usecases

import com.zszuev.paymentsapp.domain.entities.PaymentsResult
import com.zszuev.paymentsapp.domain.repository.PaymentsRepository

class GetPaymentsUseCase(private val repository: PaymentsRepository) {

    suspend operator fun invoke() = repository.getPayments()
}