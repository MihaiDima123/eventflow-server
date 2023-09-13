package com.eventflow.eventflow.modules.users.entitiy

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val email: String,
    val password: String,

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    val permission: UserPermission? = null
)