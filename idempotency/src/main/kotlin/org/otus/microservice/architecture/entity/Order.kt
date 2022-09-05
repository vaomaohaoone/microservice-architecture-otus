package org.otus.microservice.architecture.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(schema = "otus", name = "order")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Version
    var version: Long? = null,
    @Column(name = "user_name")
    var userName: String? = null,
    @Column(name = "sum_order")
    var sumOrder: BigDecimal? = null
)
