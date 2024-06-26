package com.github.smartcctv.domain.account.business.data

import com.github.smartcctv.domain.account.persistence.Account
import com.github.smartcctv.domain.account.persistence.AccountRole

data class AccountResponse(
    val id: String,
    val role: AccountRole,
    val username: String,
    val nickname: String,
    val avatarUrl: String,
) {
    companion object {
        fun of(account: Account) = AccountResponse(
            account.id!!.toString(),
            account.role,
            account.username,
            account.nickname,
            account.avatarUrl,
        )
    }
}
