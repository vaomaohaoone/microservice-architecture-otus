package org.otus.microservice.architecture.saga.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "otus", name = "payment")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID? = null,
    @Column(name = "user_id")
    var userId: String,
    @Column(name = "sum")
    var sum: BigDecimal
)
