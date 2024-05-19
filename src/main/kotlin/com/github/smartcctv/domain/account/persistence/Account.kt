package com.github.smartcctv.domain.account.persistence

import com.github.smartcctv.common.persistence.BaseTimeEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "account", indexes = [
    Index(name="idx_username", columnList="username", unique=true),
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
