package org.otus.microservice.architecture.saga.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(schema = "otus", name = "order")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "user_name")
    var userName: String,
    @Column(name = "sum_order")
    var sumOrder: BigDecimal,
    @Column(name = "e_mail")
    var email: String? = null,
    @Column(name = "status")
    var status: String,
    @Column(name = "product_id")
    var productId: String,
    @Column(name = "product_amount")
    var productAmount: Int
)
