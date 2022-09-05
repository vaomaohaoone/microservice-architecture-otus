package org.otus.microservice.architecture.saga.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "otus", name = "courier")
data class Courier(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID? = null,
    @Column(name = "courier_number")
    var courierNumber: String,
    @Column(name = "order_id")
    var orderId: Long? = null
)
