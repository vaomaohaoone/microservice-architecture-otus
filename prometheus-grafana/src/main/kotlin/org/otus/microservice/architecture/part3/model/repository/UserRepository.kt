package org.otus.microservice.architecture.part3.model.repository

import org.otus.microservice.architecture.part3.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {
}