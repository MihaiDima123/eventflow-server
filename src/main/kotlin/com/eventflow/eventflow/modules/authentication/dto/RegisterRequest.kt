package com.eventflow.eventflow.modules.authentication.dto

class RegisterRequest(
    val id: Int? = null,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val password: String,
)