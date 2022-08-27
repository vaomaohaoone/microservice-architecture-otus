package org.otus.micarch.hw5.model

data class UserData(
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String,
    var phone: String? = null
)
