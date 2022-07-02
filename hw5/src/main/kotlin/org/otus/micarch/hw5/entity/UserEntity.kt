package org.otus.micarch.hw5.entity

import javax.persistence.*

@Entity
@Table(schema = "otus", name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "user_name")
    var username: String,
    @Column(name = "password")
    var password: String,
    @Column(name = "first_name")
    var firstName: String? = null,
    @Column(name = "last_name")
    var lastName: String? = null,
    @Column(name = "email")
    var email: String,
    @Column(name = "phone")
    var phone: String? = null
)
