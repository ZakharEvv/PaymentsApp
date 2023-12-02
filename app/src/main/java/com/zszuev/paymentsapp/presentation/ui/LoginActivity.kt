package com.zszuev.paymentsapp.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.zszuev.paymentsapp.R
import com.zszuev.paymentsapp.data.PaymentsRepositoryImpl
import com.zszuev.paymentsapp.databinding.ActivityHomeBinding
import com.zszuev.paymentsapp.databinding.ActivityLoginBinding
import com.zszuev.paymentsapp.domain.entities.LoginResult
import com.zszuev.paymentsapp.domain.repository.PaymentsRepository
import com.zszuev.paymentsapp.presentation.viewmodels.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObservers()
        setupListeners()
        setupTextListeners()
    }

    private fun setupObservers() {
        viewModel.isLogged.observe(this) {
            if (it)
                startHomeActivity()
        }
        viewModel.loginResult.observe(this) {
            if (it is LoginResult.Success)
                startHomeActivity()
            else if (it is LoginResult.InvalidCredentials)
                setInvalidCredentialsError()
            else if (it is LoginResult.NetworkError) {
                Snackbar.make(binding.root, getString(R.string.network_error), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry)) { checkFieldsAndLogin() }.show()
            }
        }
    }

    private fun setupListeners() {
        binding.btnSignIn.setOnClickListener {
            checkFieldsAndLogin()
        }
        binding.btnSignUp.setOnClickListener {
            startRegisterActivity()
        }
    }

    private fun checkFieldsAndLogin() {
        val login = binding.editTextLogin.text.toString()
        val password = binding.editTextPassword.text.toString()
        if (login.isBlank())
            binding.textFieldLogin.error = getString(R.string.login_cannot_be_empty)
        if (password.isBlank())
            binding.textFieldPassword.error = getString(R.string.password_cannot_be_empty)
        if (login.isNotBlank() && password.isNotBlank())
            viewModel.login(login, password)
    }

    private fun setupTextListeners() {
        binding.editTextLogin.addTextChangedListener {
            binding.textFieldLogin.error = null
        }
        binding.editTextPassword.addTextChangedListener {
            binding.textFieldPassword.error = null
        }
    }

    private fun setInvalidCredentialsError() {
        binding.editTextPassword.text = SpannableStringBuilder("")
        binding.textFieldPassword.error = getString(R.string.check_log_passw)
    }

    private fun startRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}








