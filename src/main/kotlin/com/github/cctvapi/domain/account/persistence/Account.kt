package com.github.cctvapi.domain.account.persistence

import com.github.cctvapi.common.persistence.BaseTimeEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "account", indexes = [
//    Index(name="idx_username", columnList="username", unique=true),
    Index(name="idx_username", columnList="username"),
])
class Account(

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: AccountRole,

    @Column(unique = true)
    val username: String,

    @Column
    val password: String,

    @Column
    val nickname: String,

    @Column
    val avatarUrl: String,

    id: UUID?,
) : BaseTimeEntity(id)
