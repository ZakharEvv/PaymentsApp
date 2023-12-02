package com.zszuev.paymentsapp.data.network

import com.zszuev.paymentsapp.data.dto.LoginDTO
import com.zszuev.paymentsapp.data.dto.PaymentsResponse
import com.zszuev.paymentsapp.data.dto.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @Headers(
        "app-key: 12345",
        "v: 1"
    )
    @POST("login")
    suspend fun login(@Body login: LoginDTO): Response<TokenResponse>

    @Headers(
        "app-key: 12345",
        "v: 1"
    )
    @GET("payments")
    suspend fun loadPayments(@Header("token") token: String): Response<PaymentsResponse>
}