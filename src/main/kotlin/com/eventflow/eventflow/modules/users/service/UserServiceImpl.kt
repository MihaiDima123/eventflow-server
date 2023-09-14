package com.eventflow.eventflow.modules.users.service

import com.eventflow.eventflow.exception.EventFlowUniqueException
import com.eventflow.eventflow.modules.authentication.dto.RegisterRequest
import com.eventflow.eventflow.modules.users.entitiy.User
import com.eventflow.eventflow.modules.users.repository.UserPermissionRepository
import com.eventflow.eventflow.modules.users.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userPermissionRepository: UserPermissionRepository,
    private val passwordEncoder: PasswordEncoder
): UserService {

    override fun register(registerRequest: RegisterRequest) {
        if (countByEmail(registerRequest.email) > 0) {
            throw EventFlowUniqueException()
        }

        userRepository.save(
            User(
                email = registerRequest.email,
                password = passwordEncoder.encode(registerRequest.password),
                firstName = registerRequest.firstName,
                lastName = registerRequest.lastName,
                phoneNumber = registerRequest.phoneNumber,
                permission = userPermissionRepository.findOneByName("USER")
            )
        )
    }

    private fun countByEmail(email: String): Int {
        return userRepository.countByEmail(email)
    }

}