package org.otus.microservice.architecture.saga.repository

import org.otus.microservice.architecture.saga.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PaymentRepository : JpaRepository<Payment, UUID> {
    fun findByUserId(userId: String): Payment?
    fun deleteByUserId(userId: String)
}