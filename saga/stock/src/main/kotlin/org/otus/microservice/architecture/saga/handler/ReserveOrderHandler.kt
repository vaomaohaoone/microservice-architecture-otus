package org.otus.microservice.architecture.saga.handler

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.value.IntegerValue
import org.camunda.bpm.engine.variable.value.StringValue
import org.otus.microservice.architecture.saga.service.ProductStockService
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
@ExternalTaskSubscription("reserve-order")
class ReserveOrderHandler(
    private val productStockService: ProductStockService
) : ExternalTaskHandler {

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        val productId = externalTask.getVariableTyped<StringValue>("PRODUCT_ID").value
        val amount = externalTask.getVariableTyped<IntegerValue>("PRODUCT_AMOUNT").value
        try {
            productStockService.chargeAmount(productId, amount)
            externalTaskService.complete(externalTask)
        } catch (e: Exception) {
            externalTaskService.handleBpmnError(externalTask, "SAGA_ERROR", e.message);
        }
    }
}