package com.eventflow.eventflow.modules.users.service

import com.eventflow.eventflow.modules.authentication.dto.RegisterRequest

interface UserService {
    fun register(registerRequest: RegisterRequest)
}