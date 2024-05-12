package com.github.cctvapi.common.env

import com.github.cctvapi.domain.account.business.AccountService
import com.github.cctvapi.domain.account.business.data.AccountCreation
import com.github.cctvapi.domain.account.persistence.Account
import com.github.cctvapi.domain.account.persistence.AccountRole
import com.github.cctvapi.domain.device.persistence.IotDevice
import com.github.cctvapi.domain.device.persistence.IotDeviceRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev")
class InitRunnerDev(
    private val accountService: AccountService,
    private val iorDeviceRepository: IotDeviceRepository,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        if (alreadyInitialized()) {
            return
        }

        val users = ArrayList<Account>()
        users.add(createAdmin())
        users.addAll(createUsers(1, 5))

        iorDeviceRepository.save(IotDevice())
        iorDeviceRepository.save(IotDevice())
        iorDeviceRepository.save(IotDevice())
    }

    private fun alreadyInitialized(): Boolean {
        val user = accountService.findAll()

        return user.isNotEmpty()
    }

    private fun createAdmin(): Account {
        val creation = AccountCreation(
            AccountRole.ADMIN,
            "admin",
            "admin",
            "admin",
        )
        return accountService.create(creation)
    }

    private fun createUsers(initNum: Int, size: Int): List<Account> {
        val result = ArrayList<Account>()

        val maxNum = initNum + size - 1
        for (i in initNum .. maxNum) {
            val creation = AccountCreation(
                AccountRole.MEMBER,
                "user${i}@gmail.com",
                "1234",
                "user$i",
            )
            val account: Account = accountService.create(creation)

            result.add(account)
        }

        return result
    }
}