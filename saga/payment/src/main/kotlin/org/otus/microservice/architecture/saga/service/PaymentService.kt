package org.otus.microservice.architecture.saga.service

import org.otus.microservice.architecture.saga.entity.Payment
import org.otus.microservice.architecture.saga.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.math.BigDecimal
import javax.persistence.EntityNotFoundException

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {
    @Transactional
    fun createOrUpdatePayment(payment: Payment) = paymentRepository.save(payment)

    fun getPaymentByUserId(userId: String): Payment =
        paymentRepository.findByUserId(userId) ?: throw EntityNotFoundException()

    fun deletePaymentByUserId(userId: String) {
        getPaymentByUserId(userId)
        paymentRepository.deleteByUserId(userId)
    }

    @Transactional
    fun chargeMoney(userId: String, sum: BigDecimal) {
        val payment = getPaymentByUserId(userId)
        if (payment.sum < sum)
            throw RuntimeException("Wtf sum on account is less than charged")
        payment.sum = payment.sum.minus(sum)
        paymentRepository.save(payment)
    }

    @Transactional
    fun increaseMoney(userId: String, sum: BigDecimal) {
        val payment = getPaymentByUserId(userId)
        payment.sum = payment.sum.plus(sum)
        paymentRepository.save(payment)
    }

    fun listAllPayments(): List<Payment> = paymentRepository.findAll()
}