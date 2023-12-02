package com.zszuev.paymentsapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zszuev.paymentsapp.data.PaymentsRepositoryImpl
import com.zszuev.paymentsapp.domain.entities.LoginResult
import com.zszuev.paymentsapp.domain.usecases.IsLoggedInUseCase
import com.zszuev.paymentsapp.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PaymentsRepositoryImpl(application)

    private val isLoggedInUseCase = IsLoggedInUseCase(repository)
    private val loginUseCase = LoginUseCase(repository)

    private val _loginState = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginState

    private val _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean> = _isLogged

    init {
        checkIsLogged()
    }

    private fun checkIsLogged() {
        _isLogged.value = isLoggedInUseCase()
    }

    fun login(login: String, password: String) {
        viewModelScope.launch {
            _loginState.value = loginUseCase(login, password)!!
        }
    }
}