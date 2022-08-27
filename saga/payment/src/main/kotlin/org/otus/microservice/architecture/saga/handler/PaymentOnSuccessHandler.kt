package org.otus.microservice.architecture.saga.handler

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.value.NumberValue
import org.camunda.bpm.engine.variable.value.StringValue
import org.otus.microservice.architecture.saga.repository.PaymentRepository
import org.otus.microservice.architecture.saga.service.PaymentService
import org.springframework.stereotype.Component
import java.lang.Exception
import java.math.BigDecimal

@Component
@ExternalTaskSubscription("payment-success")
class PaymentOnSuccessHandler(
    private val paymentService: PaymentService
) : ExternalTaskHandler {

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        val userId = externalTask.getVariableTyped<StringValue>("USER_ID").value
        val sum = externalTask.getVariableTyped<NumberValue>("SUM").value as BigDecimal
        try {
            paymentService.chargeMoney(userId, sum)
            externalTaskService.complete(externalTask)
        } catch (e: Exception) {
            externalTaskService.handleBpmnError(externalTask, "SAGA_ERROR", e.message);
        }
    }
}