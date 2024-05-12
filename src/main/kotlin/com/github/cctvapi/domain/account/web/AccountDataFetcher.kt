package com.github.cctvapi.domain.account.web

import com.github.cctvapi.domain.account.business.AccountService
import com.github.cctvapi.domain.account.business.data.AccountCreation
import com.github.cctvapi.domain.account.business.data.AccountResponse
import com.github.cctvapi.domain.account.persistence.Account
import com.netflix.graphql.dgs.*
import org.springframework.security.core.Authentication
import java.util.*

@DgsComponent
class AccountDataFetcher(
    private val accountService: AccountService,
) {

    @DgsQuery
    fun accountsAll(): List<Account> {
        val accounts = accountService.findAll()
        return accounts
    }

    @DgsQuery
    fun account(@InputArgument username: String?, authentication: Authentication): Account? {
        if (username !== null) {
            return accountService.findByUsername(username)
        } else {
            val accountResponse = authentication.details as AccountResponse
            val id = UUID.fromString(accountResponse.id)
            return accountService.findById(id)
        }
    }

    @DgsQuery
    fun accounts(@InputArgument id: UUID): Account? {
        return accountService.findById(id)
    }

    @DgsMutation
    fun createAccount(creation: AccountCreation): Account {
        return accountService.create(creation)
    }
}
