package org.otus.microservice.architecture.saga.handler

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.value.StringValue
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
@ExternalTaskSubscription("send-email")
class SendEmailHandler : ExternalTaskHandler {

    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        val email = externalTask.getVariableTyped<StringValue>("CLIENT_EMAIL").value
            ?: throw RuntimeException("Cannot send email message to client")
        println("Email was sent to client on $email")
    }
}