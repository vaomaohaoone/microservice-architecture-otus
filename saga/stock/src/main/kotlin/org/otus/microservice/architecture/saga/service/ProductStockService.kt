package org.otus.microservice.architecture.saga.service

import org.otus.microservice.architecture.saga.entity.ProductStock
import org.otus.microservice.architecture.saga.repository.ProductStockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class ProductStockService(
    private val productStockRepository: ProductStockRepository
) {

    @Transactional
    fun createOrUpdateProductStock(productStock: ProductStock) = productStockRepository.save(productStock)

    fun getProductStockByProductId(productId: String): ProductStock =
        productStockRepository.findByProductId(productId) ?: throw EntityNotFoundException()

    fun deleteProductStockByProductId(productId: String) {
        getProductStockByProductId(productId)
        productStockRepository.deleteByProductId(productId)
    }

    @Transactional
    fun chargeAmount(productId: String, amount: Int) {
        val productStock = getProductStockByProductId(productId)
        if (productStock.productAmount < amount)
            throw RuntimeException("Wtf amount on stock is less than charged")
        productStock.productAmount = productStock.productAmount.minus(amount)
        productStockRepository.save(productStock)
    }

    @Transactional
    fun increaseAmount(productId: String, amount: Int) {
        val productStock = getProductStockByProductId(productId)
        productStock.productAmount = productStock.productAmount.plus(amount)
        productStockRepository.save(productStock)
    }

    fun listAllProductStocks(): List<ProductStock> = productStockRepository.findAll()

}