package com.eventflow.eventflow.modules.users.controller

import com.eventflow.eventflow.modules.users.dao.LoginRequest
import com.eventflow.eventflow.modules.users.dao.RegisterRequest
import com.eventflow.eventflow.modules.users.entitiy.User
import com.eventflow.eventflow.modules.users.repository.UserPermissionRepository
import com.eventflow.eventflow.modules.users.repository.UserRepository
import com.eventflow.eventflow.modules.users.utils.JwtUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val userPermissionRepository: UserPermissionRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtils: JwtUtils
) {
    @PostMapping("/auth/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.email,
                loginRequest.password
            )
        )

        return ResponseEntity.ok(jwtUtils.generateJwtToken(authentication))
    }

    @PostMapping("/auth/register")
    fun register(@RequestBody registerRequest: RegisterRequest) {
        userRepository.save(User(
            email = registerRequest.email,
            password = passwordEncoder.encode(registerRequest.password),
            permission = userPermissionRepository.findOneByName("USER")
        ))
    }

}