package org.otus.microservice.architecture.saga.controller

import org.otus.microservice.architecture.saga.entity.Courier
import org.otus.microservice.architecture.saga.service.CourierService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController("/courier")
class CourierController(
    private val courierService: CourierService
) {

    @PostMapping
    fun createOrUpdateCourier(@RequestBody courier: Courier) = courierService.createOrUpdateCourier(courier)

    @GetMapping("/{courierId}")
    fun getCourierById(@PathVariable("courierId") courierId: UUID) = courierService.getCourierById(courierId)

    @DeleteMapping("/{courierId}")
    fun deleteCourierById(@PathVariable("courierId") courierId: UUID) = courierService.deleteCourierById(courierId)

    @GetMapping
    fun listAllCouriers() = courierService.listAllCouriers()
}