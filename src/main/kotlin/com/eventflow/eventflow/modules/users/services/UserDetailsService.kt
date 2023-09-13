package com.eventflow.eventflow.modules.users.services

import com.eventflow.eventflow.modules.users.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username)
        .orElseThrow {
            UsernameNotFoundException("Username not found")
        }

        return User(user.email, user.password, listOf(
            SimpleGrantedAuthority(user.permission?.name ?: "USER")
        ))
    }

}
