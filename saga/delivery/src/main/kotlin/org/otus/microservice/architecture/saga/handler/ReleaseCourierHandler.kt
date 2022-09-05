package org.otus.microservice.architecture.saga.handler

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.value.LongValue
import org.otus.microservice.architecture.saga.service.CourierService
import org.springframework.stereotype.Component

@Component
@ExternalTaskSubscription("release-courier")
class ReleaseCourierHandler(
    private val courierService: CourierService
) : ExternalTaskHandler {

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        val orderId = externalTask.getVariableTyped<LongValue>("ORDER_ID").value
        courierService.releaseCourier(orderId)
        externalTaskService.complete(externalTask)
    }
}