package org.otus.microservice.architecture.saga.repository

import org.otus.microservice.architecture.saga.entity.ProductStock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductStockRepository: JpaRepository<ProductStock, UUID> {
    fun findByProductId(productId: String): ProductStock?
    fun deleteByProductId(productId: String)
}