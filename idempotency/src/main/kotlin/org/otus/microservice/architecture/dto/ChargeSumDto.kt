package org.otus.microservice.architecture.dto

import java.math.BigDecimal

data class ChargeSumDto(
    val orderId: Long,
    val sum: BigDecimal
)
