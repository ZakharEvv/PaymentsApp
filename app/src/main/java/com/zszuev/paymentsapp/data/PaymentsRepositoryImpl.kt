package com.zszuev.paymentsapp.data

import android.app.Application
import com.zszuev.paymentsapp.data.dto.LoginDTO
import com.zszuev.paymentsapp.data.mappers.PaymentMapper
import com.zszuev.paymentsapp.data.network.ApiFactory
import com.zszuev.paymentsapp.data.utils.UserManager
import com.zszuev.paymentsapp.domain.entities.LoginResult
import com.zszuev.paymentsapp.domain.entities.Payment
import com.zszuev.paymentsapp.domain.entities.PaymentsResult
import com.zszuev.paymentsapp.domain.repository.PaymentsRepository

class PaymentsRepositoryImpl(application: Application) : PaymentsRepository {

    private val userManager = UserManager(application)
    private val paymentMapper = PaymentMapper()
    private val apiService = ApiFactory.apiService

    override fun checkIsLogged() = userManager.isLogged()

    override suspend fun login(login: String, password: String): LoginResult {
        return try {
            val response = apiService.login(LoginDTO(login, password)).body()
            if (response!!.isSuccess) {
                userManager.saveToken(response.response.token)
                LoginResult.Success
            } else
                LoginResult.InvalidCredentials
        } catch (_: Exception) {
            LoginResult.NetworkError
        }
    }

    override fun logout() {
        userManager.clearToken()
    }

    override suspend fun getPayments(): PaymentsResult {
        return try {
            val response = apiService.loadPayments(userManager.getToken() ?: "").body()
            if (response!!.isSuccess)
                PaymentsResult.Success(
                    paymentMapper.mapListDTOToListEntity(response.payments)
                )
            else
                PaymentsResult.Error
        } catch (_: Exception) {
            PaymentsResult.NetworkError
        }
    }
}