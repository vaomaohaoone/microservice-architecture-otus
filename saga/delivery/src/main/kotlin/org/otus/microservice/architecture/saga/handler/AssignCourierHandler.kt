package org.otus.microservice.architecture.saga.handler

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.value.LongValue
import org.otus.microservice.architecture.saga.service.CourierService
import org.springframework.stereotype.Component

@Component
@ExternalTaskSubscription("assign-courier")
class AssignCourierHandler(
    private val courierService: CourierService
) : ExternalTaskHandler {

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        val orderId = externalTask.getVariableTyped<LongValue>("ORDER_ID").value
        try {
            courierService.assignToOrder(orderId)
            externalTaskService.complete(externalTask)
        } catch (e: Exception) {
            externalTaskService.handleBpmnError(externalTask, "SAGA_ERROR", e.message);
        }
    }
}