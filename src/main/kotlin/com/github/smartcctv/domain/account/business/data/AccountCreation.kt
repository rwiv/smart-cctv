package com.github.smartcctv.domain.account.business.data

import com.github.smartcctv.domain.account.persistence.AccountRole

data class AccountCreation(
    val role: AccountRole,
    val username: String,
    val password: String,
    val nickname: String
)