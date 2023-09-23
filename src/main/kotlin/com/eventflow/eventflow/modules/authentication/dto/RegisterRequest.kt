package com.eventflow.eventflow.modules.authentication.dto

class RegisterRequest(
    val id: Int? = null,
    val email: String,
    val password: String,
)