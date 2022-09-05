package org.otus.microservice.architecture.saga.service

import org.otus.microservice.architecture.saga.entity.Courier
import org.otus.microservice.architecture.saga.repository.CourierRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class CourierService(
    private val courierRepository: CourierRepository
) {
    fun createOrUpdateCourier(courier: Courier) = courierRepository.save(courier)

    fun getCourierById(id: UUID): Courier = courierRepository.findById(id).orElseThrow {
        EntityNotFoundException("Courier with id:$id not found")
    }

    fun deleteCourierById(id: UUID) {
        getCourierById(id)
        courierRepository.deleteById(id)
    }

    fun listAllCouriers(): List<Courier> = courierRepository.findAll()

    @Transactional
    fun assignToOrder(orderId: Long) {
        val couriers = courierRepository.findAllByOrderIdIsNull()
        if (couriers.isEmpty())
            throw RuntimeException("There is no free couriers")
        val courier = couriers[0]
        courier.orderId = orderId
        courierRepository.save(courier)
    }

    @Transactional
    fun releaseCourier(orderId: Long) {
        val courier = courierRepository.findByOrderId(orderId)
        courier.orderId = null
        courierRepository.save(courier)
    }
}