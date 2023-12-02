package com.zszuev.paymentsapp.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.zszuev.paymentsapp.R
import com.zszuev.paymentsapp.databinding.ActivityLoginBinding
import com.zszuev.paymentsapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()
        setupTextListeners()
    }

    private fun setupListeners() {
        binding.btnSignUp.setOnClickListener {
            checkFieldsAndRegister()
        }
        binding.btnSignIn.setOnClickListener {
            startLoginActivity()
        }
    }

    private fun setupTextListeners() {
        binding.editTextLogin.addTextChangedListener {
            binding.textFieldLogin.error = null
        }
        binding.editTextPassword.addTextChangedListener {
            binding.textFieldPassword.error = null
        }
        binding.editTextRepeatPassword.addTextChangedListener {
            binding.textFieldRepeatPassword.error = null
        }
    }

    private fun checkFieldsAndRegister() {
        val login = binding.editTextLogin.text.toString()
        val password = binding.editTextPassword.text.toString()
        val repeatedPassword = binding.editTextRepeatPassword.text.toString()
        if (login.isBlank())
            binding.textFieldLogin.error = getString(R.string.login_cannot_be_empty)
        if (password.isBlank())
            binding.textFieldPassword.error = getString(R.string.password_cannot_be_empty)
        if (password != repeatedPassword)
            binding.textFieldRepeatPassword.error = getString(R.string.passw_diff)
        if (login.isNotBlank() && password.isNotBlank() && password == repeatedPassword)
            startLoginActivity()
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}