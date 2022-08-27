package org.otus.microservice.architecture.saga.service

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.variable.value.LongValue
import org.springframework.stereotype.Component

@Component("ChangeStatusSuccess")
class ChangeStatusSuccess(
    private val orderService: OrderService
) : JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        val orderId = execution.getVariableTyped<LongValue>("ORDER_ID").value
        orderService.changeStatus(orderId, "SUCCESS")
    }
}