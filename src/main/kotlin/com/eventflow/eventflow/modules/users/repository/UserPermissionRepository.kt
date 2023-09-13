package com.eventflow.eventflow.modules.users.repository

import com.eventflow.eventflow.modules.users.entitiy.UserPermission
import org.springframework.data.repository.Repository

interface UserPermissionRepository: Repository<UserPermission, Int> {
    fun findOneByName(name: String): UserPermission
}