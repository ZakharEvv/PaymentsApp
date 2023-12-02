package com.zszuev.paymentsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.zszuev.paymentsapp.databinding.PaymentItemBinding
import com.zszuev.paymentsapp.domain.entities.Payment

class PaymentAdapter : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    var payments = listOf<Payment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val onItemClickListener: ((Payment) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = PaymentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun getItemCount() = payments.size

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(payments[position], onItemClickListener)
    }

    class PaymentViewHolder(private val binding: PaymentItemBinding) : ViewHolder(binding.root) {
        fun bind(payment: Payment, onItemClickListener: ((Payment) -> Unit)?) {
            binding.tvTitle.text = payment.title
            binding.tvTime.text = payment.created
            binding.tvAmount.text = payment.amount
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(payment)
            }
        }
    }
}