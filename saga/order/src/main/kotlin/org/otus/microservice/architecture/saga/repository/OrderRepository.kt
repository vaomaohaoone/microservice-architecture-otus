package org.otus.microservice.architecture.saga.repository

import org.otus.microservice.architecture.saga.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long>