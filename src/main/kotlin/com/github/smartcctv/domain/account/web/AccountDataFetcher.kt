package com.github.smartcctv.domain.account.web

import com.github.smartcctv.domain.account.business.AccountService
import com.github.smartcctv.domain.account.business.data.AccountCreation
import com.github.smartcctv.domain.account.business.data.AccountResponse
import com.github.smartcctv.domain.account.persistence.Account
import com.github.smartcctv.domain.device.persistence.IotDevice
import com.github.smartcctv.domain.device.persistence.IotDeviceRepository
import com.netflix.graphql.dgs.*
import org.springframework.security.core.Authentication
import java.util.*

@DgsComponent
class AccountDataFetcher(
    private val accountService: AccountService,
    private val iotDeviceRepository: IotDeviceRepository,
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

    @DgsData(parentType = "Account")
    fun devices(dfe: DgsDataFetchingEnvironment): List<IotDevice> {
        val account = dfe.getSource<Account>()
        return iotDeviceRepository.findByOwner(account)
    }
}
