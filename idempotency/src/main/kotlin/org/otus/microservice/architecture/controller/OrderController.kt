package org.otus.microservice.architecture.controller

import org.otus.microservice.architecture.dto.ChargeSumDto
import org.otus.microservice.architecture.entity.Order
import org.otus.microservice.architecture.service.OrderService
import org.springframework.http.HttpHeaders.IF_MATCH
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping("/order")
    fun createOrUpdateOrder(@RequestBody order: Order) = orderService.createOrUpdateOrder(order)

    @GetMapping("/order/{id}")
    fun getOrderByOrderId(@PathVariable("id") orderId: Long) = orderService.getOrderByIdAndGetResponse(orderId)

    @DeleteMapping("/order/{id}")
    fun deleteOrderById(@PathVariable("id") orderId: Long, @RequestHeader(IF_MATCH) version: Long) =
        orderService.deleteOrderById(orderId, version)

    @PutMapping("/order/charge")
    fun chargeSumFromOrder(@RequestBody dto: ChargeSumDto, @RequestHeader(IF_MATCH) version: Long) =
        orderService.chargeSumFromOrderAndGetVersion(dto.orderId, version, dto.sum)

    @GetMapping("/orders")
    fun listAllOrders() = orderService.listAllOrders()

}