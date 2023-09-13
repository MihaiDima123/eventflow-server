package com.eventflow.eventflow.modules.users.repository

import com.eventflow.eventflow.modules.users.entitiy.User
import org.springframework.data.repository.Repository
import java.util.Optional

interface UserRepository: Repository<User, Int> {
    fun countByEmail(email: String?): Int
    fun findByEmail(email: String?): Optional<User>
    fun save(user: User): User
}