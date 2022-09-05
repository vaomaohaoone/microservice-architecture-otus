package org.otus.microservice.architecture.saga.service

import org.camunda.bpm.engine.RuntimeService
import org.otus.microservice.architecture.saga.entity.Order
import org.otus.microservice.architecture.saga.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val runtimeService: RuntimeService
) {

    fun createOrUpdateOrder(order: Order) = orderRepository.save(order)

    fun getOrderById(id: Long): Order = orderRepository.findById(id).orElseThrow {
        EntityNotFoundException("Order with id:$id not found")
    }

    fun deleteOrderById(id: Long) {
        getOrderById(id)
        orderRepository.deleteById(id)
    }

    fun listAllOrders(): List<Order> = orderRepository.findAll()

    fun startProcess(orderId: Long) {
        val businessKey = "order_$orderId"
        val order = orderRepository.findById(orderId).orElseThrow()
        runtimeService.startProcessInstanceById(PROCESS_ID, businessKey, mapOf(
            Pair("ORDER_ID", orderId),
            Pair("SUM", order.sumOrder),
            Pair("USER_ID", order.userName),
            Pair("PRODUCT_ID", order.productId),
            Pair("PRODUCT_AMOUNT", order.productAmount),
            Pair("CLIENT_EMAIL", order.email)
        ))
    }

    @Transactional
    fun changeStatus(orderId: Long, status: String) {
        val order = orderRepository.findById(orderId).orElseThrow()
        order.status = status
        orderRepository.save(order)
    }

}