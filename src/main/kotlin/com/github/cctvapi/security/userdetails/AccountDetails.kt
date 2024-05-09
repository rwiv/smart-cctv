package com.github.cctvapi.security.userdetails

import com.github.cctvapi.domain.account.persistence.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class AccountDetails(
    account: Account,
    roles: List<GrantedAuthority>
) : User(
    account.username,
    account.password,
    true, true, true, true,
    roles
) {
    val id = account.id!!
}
