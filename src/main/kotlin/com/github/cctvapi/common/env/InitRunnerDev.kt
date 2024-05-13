package com.github.cctvapi.common.env

import com.github.cctvapi.common.error.exception.NotFoundException
import com.github.cctvapi.domain.account.business.AccountService
import com.github.cctvapi.domain.account.business.data.AccountCreation
import com.github.cctvapi.domain.account.persistence.Account
import com.github.cctvapi.domain.account.persistence.AccountRole
import com.github.cctvapi.domain.device.business.IotDeviceService
import com.github.cctvapi.domain.device.business.LiveService
import com.github.cctvapi.domain.device.business.data.IotDeviceCreation
import com.github.cctvapi.domain.device.business.data.LiveCreation
import com.github.cctvapi.domain.video.business.VideoService
import com.github.cctvapi.domain.video.peresistence.VideoRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev")
class InitRunnerDev(
    private val accountService: AccountService,
    private val iotDeviceService: IotDeviceService,
    private val liveService: LiveService,
    private val videoRepository: VideoRepository,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        if (alreadyInitialized()) {
            return
        }

        val users = ArrayList<Account>()
        users.add(createAdmin())
        users.addAll(createUsers(1, 5))

        val admin = users.find { it.role === AccountRole.ADMIN }
            ?: throw NotFoundException("not found admin account")

        val d1 = iotDeviceService.create(IotDeviceCreation(admin.id!!, "device1"))
        val d2 = iotDeviceService.create(IotDeviceCreation(admin.id!!, "device2"))

        val l1 = liveService.create(LiveCreation(d1.id!! ,"d1 live"))
        val l2 = liveService.create(LiveCreation(d2.id!! ,"d2 live"))

        val v1 = l1.video
        // https://picsum.photos/320/180
        v1.updateThumbnailUrl("https://fastly.picsum.photos/id/25/320/180.jpg?hmac=oMwJDZ4t8TIJIh4wvQjVcSNhlljAnP-yJpIuOXrDXcA")
        videoRepository.save(v1)


        val v2 = l2.video
        v2.updateThumbnailUrl("https://fastly.picsum.photos/id/368/320/180.jpg?hmac=1SeoQS2Dc8M1rDUZucAzGDR3Pw1YWNpWEVfO6nliBuc")
        videoRepository.save(v2)
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