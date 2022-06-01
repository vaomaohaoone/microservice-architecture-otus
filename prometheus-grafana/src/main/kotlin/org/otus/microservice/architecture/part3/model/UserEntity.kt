package org.otus.microservice.architecture.part3.model

import javax.persistence.*

@Entity
@Table(schema = "otus", name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "user_name")
    var username: String,

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "last_name")
    var lastName: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "phone")
    var phone: String? = null
)