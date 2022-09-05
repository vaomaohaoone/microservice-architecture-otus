package org.otus.microservice.architecture.saga.controller

import org.otus.microservice.architecture.saga.entity.Order
import org.otus.microservice.architecture.saga.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController("/order")
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping
    fun createOrUpdateOrder(@RequestBody order: Order) = orderService.createOrUpdateOrder(order)

    @GetMapping("/{id}")
    fun getOrderByOrderId(@PathVariable("id") orderId: Long) = orderService.getOrderById(orderId)

    @DeleteMapping("/{id}")
    fun deleteOrderById(@PathVariable("id") orderId: Long) = orderService.deleteOrderById(orderId)

    @GetMapping
    fun listAllOrders() = orderService.listAllOrders()

    @PostMapping("/{id}")
    fun startProcess(@PathVariable("id") orderId: Long) {
        orderService.startProcess(orderId)
    }
}