package com.zszuev.paymentsapp.data.mappers

import com.zszuev.paymentsapp.data.dto.PaymentDTO
import com.zszuev.paymentsapp.domain.entities.Payment
import java.text.SimpleDateFormat
import java.util.Locale

class PaymentMapper {

    private val TITLE_UNDEFINED = "No title"
    private val AMOUNT_UNDEFINED = "0"
    private val DATE_UNDEFINED = "No date"

    private fun mapDTOTOEntity(paymentDTO: PaymentDTO): Payment {
        if (paymentDTO.title.isNullOrBlank())
            paymentDTO.title = TITLE_UNDEFINED
        if (paymentDTO.amount.isNullOrBlank())
            paymentDTO.amount = AMOUNT_UNDEFINED

        return Payment(
            paymentDTO.id,
            paymentDTO.title,
            paymentDTO.amount + "$",
            timestampToFormattedDateTime(paymentDTO.created)
        )
    }


    fun mapListDTOToListEntity(payments: List<PaymentDTO>) =
        payments.map { mapDTOTOEntity(it) }

    private fun timestampToFormattedDateTime(timestamp: Int): String {
        return if (timestamp != 0)
            SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.ENGLISH).format(timestamp * 1000L)
        else
            DATE_UNDEFINED
    }
}