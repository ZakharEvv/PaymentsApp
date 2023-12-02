package com.zszuev.paymentsapp.presentation.ui

import android.app.Notification.Action
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.zszuev.paymentsapp.R
import com.zszuev.paymentsapp.data.PaymentsRepositoryImpl
import com.zszuev.paymentsapp.databinding.ActivityHomeBinding
import com.zszuev.paymentsapp.domain.entities.PaymentsResult
import com.zszuev.paymentsapp.presentation.adapters.PaymentAdapter
import com.zszuev.paymentsapp.presentation.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val adapter by lazy {
        PaymentAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.loadPayments()
        setupObservers()
        setupRecycler()
        setupRefreshListener()
        setupDrawerMenuListener()
    }

    private fun setupObservers() {
        viewModel.paymentsState.observe(this) {
            if (it is PaymentsResult.Success)
                adapter.payments = it.payments
            if (it is PaymentsResult.NetworkError)
                Snackbar.make(binding.root, getString(R.string.network_error), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry)) { viewModel.loadPayments() }.show()
            if (it is PaymentsResult.Error)
                Snackbar.make(binding.root, getString(R.string.unknown_error), Snackbar.LENGTH_LONG).show()
        }
        viewModel.isLoading.observe(this) {
            binding.swipeLayout.isRefreshing = it
        }
    }

    private fun setupRefreshListener() {
        binding.swipeLayout.setOnRefreshListener {
            viewModel.loadPayments()
        }
    }

    private fun setupRecycler() {
        binding.recyclerViewPayments.adapter = adapter
    }

    private fun setupDrawerMenuListener() {
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_logout -> {
                    viewModel.logout()
                    startLoginActivity()
                }
            }
            true
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}