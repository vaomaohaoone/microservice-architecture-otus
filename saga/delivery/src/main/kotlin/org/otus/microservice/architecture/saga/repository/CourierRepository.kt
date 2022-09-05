package org.otus.microservice.architecture.saga.repository

import org.otus.microservice.architecture.saga.entity.Courier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CourierRepository : JpaRepository<Courier, UUID> {
    fun findAllByOrderIdIsNull(): List<Courier>

    fun findByOrderId(orderId: Long): Courier
}