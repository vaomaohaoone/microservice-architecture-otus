package org.otus.microservice.architecture.saga.controller

import org.otus.microservice.architecture.saga.entity.Payment
import org.otus.microservice.architecture.saga.service.PaymentService
import org.springframework.web.bind.annotation.*

@RestController("/payment")
class PaymentController(
    private val paymentService: PaymentService
) {
    @PostMapping
    fun createOrUpdateOrder(@RequestBody payment: Payment) = paymentService.createOrUpdatePayment(payment)

    @GetMapping("/{userId}")
    fun getOrderByUserId(@PathVariable("userId") userId: String) = paymentService.getPaymentByUserId(userId)

    @DeleteMapping("/{userId}")
    fun deleteOrderById(@PathVariable("userId") userId: String) = paymentService.deletePaymentByUserId(userId)

    @GetMapping
    fun listAllOrders() = paymentService.listAllPayments()
}