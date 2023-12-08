package org.otus.microservice.architecture.service

import org.otus.microservice.architecture.entity.Order
import org.otus.microservice.architecture.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders.ETAG
import org.springframework.http.ResponseEntity
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import javax.persistence.EntityNotFoundException

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    @Autowired
    lateinit var orderService: OrderService

    fun createOrUpdateOrder(order: Order) = try {
        orderRepository.save(order)
    } catch (e: ObjectOptimisticLockingFailureException) {
        throw Exception("Updated version not equals with actual")
    }

    fun getOrderByIdAndGetResponse(id: Long): ResponseEntity<Order> {
        val order = getOrderById(id)
        return ResponseEntity.ok()
            .header(ETAG, order.version.toString())
            .body(order)
    }

    @Transactional(readOnly = true)
    fun getOrderById(id: Long): Order = orderRepository.findById(id).orElseThrow {
        EntityNotFoundException("Order with id:$id not found")
    }

    fun chargeSumFromOrderAndGetVersion(id: Long, version: Long, sum: BigDecimal): ResponseEntity<Any> {
        orderService.chargeSumFromOrder(id, version, sum)
        val updated = orderService.getOrderById(id)
        return ResponseEntity.ok().header(ETAG, updated.version.toString()).build()
    }

    @Transactional
    fun chargeSumFromOrder(id: Long, version: Long, sum: BigDecimal) {
        val order = getOrderById(id)
        if (version != order.version)
            throw RuntimeException("Order version not equals with actual")
        order.sumOrder = order.sumOrder?.minus(sum)
    }

    fun deleteOrderById(id: Long, version: Long) {
        val order = getOrderById(id)
        if (version != order.version)
            throw RuntimeException("Order version not equals with actual")
        orderRepository.deleteById(id)
    }

    fun listAllOrders(): List<Order> = orderRepository.findAll()
}