package com.zszuev.paymentsapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zszuev.paymentsapp.data.PaymentsRepositoryImpl
import com.zszuev.paymentsapp.domain.entities.PaymentsResult
import com.zszuev.paymentsapp.domain.usecases.GetPaymentsUseCase
import com.zszuev.paymentsapp.domain.usecases.LogoutUseCase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PaymentsRepositoryImpl(application)

    private val getPaymentsUseCase = GetPaymentsUseCase(repository)
    private val logoutUseCase = LogoutUseCase(repository)

    private val _paymentsState = MutableLiveData<PaymentsResult>()
    val paymentsState: LiveData<PaymentsResult> = _paymentsState

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadPayments() {
        viewModelScope.launch {
            _isLoading.value = true
            _paymentsState.value = getPaymentsUseCase()!!
            _isLoading.value = false
        }
    }

    fun logout() {
        logoutUseCase()
    }
}