package org.otus.microservice.architecture.saga.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "otus", name = "product_stock")
data class ProductStock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID? = null,
    @Column(name = "product_id")
    var productId: String,
    @Column(name = "product_amount")
    var productAmount: Int
)
