package org.otus.microservice.architecture.saga.controller

import org.otus.microservice.architecture.saga.entity.ProductStock
import org.otus.microservice.architecture.saga.service.ProductStockService
import org.springframework.web.bind.annotation.*

@RestController("/product-stock")
class ProductStockController(
    private val productStockService: ProductStockService
) {

    @PostMapping
    fun createOrUpdateProductStock(@RequestBody productStock: ProductStock) =
        productStockService.createOrUpdateProductStock(productStock)

    @GetMapping("/{productId}")
    fun getProductStockByProductId(@PathVariable("productId") productId: String) =
        productStockService.getProductStockByProductId(productId)

    @DeleteMapping("/{productId}")
    fun deleteProductStockByProductId(@PathVariable("productId") productId: String) =
        productStockService.deleteProductStockByProductId(productId)

    @GetMapping
    fun listAllProductStocks() = productStockService.listAllProductStocks()


}