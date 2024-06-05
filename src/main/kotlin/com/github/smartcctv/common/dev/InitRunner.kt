package com.github.smartcctv.common.dev

import com.github.smartcctv.common.secret.SecretProperties
import com.github.smartcctv.domain.account.business.AccountService
import com.github.smartcctv.domain.account.business.data.AccountCreation
import com.github.smartcctv.domain.account.persistence.Account
import com.github.smartcctv.domain.account.persistence.AccountRole
import com.github.smartcctv.domain.device.business.DeviceService
import com.github.smartcctv.domain.device.persistence.DeviceRepository
import com.github.smartcctv.domain.video.peresistence.VideoRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitRunner(
    private val accountService: AccountService,
    private val deviceService: DeviceService,
    private val videoRepository: VideoRepository,
    private val deviceRepository: DeviceRepository,
    private  val secretProperties: SecretProperties,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        if (alreadyInitialized()) {
            return
        }

        val users = ArrayList<Account>()
        users.add(createAdmin())
//        users.addAll(createUsers(1, 5))
//
//        val admin = users.find { it.role === AccountRole.ADMIN }
//            ?: throw NotFoundException("not found admin account")
//
//        val d1 = deviceService.create(DeviceCreation("device1"), admin.id!!)
//        d1.streamKey = "hello"
//        deviceRepository.save(d1)
//
//        val d2 = deviceService.create(DeviceCreation("device2"), admin.id!!)

//        val v1 = d1.live?.video ?: throw NotFoundException("not found video")
//        // https://picsum.photos/320/180
//        v1.updateThumbnailUrl("https://fastly.picsum.photos/id/25/320/180.jpg?hmac=oMwJDZ4t8TIJIh4wvQjVcSNhlljAnP-yJpIuOXrDXcA")
//        videoRepository.save(v1)
//
//        val v2 = d2.live?.video ?: throw NotFoundException("not found video")
//        v2.updateThumbnailUrl("https://fastly.picsum.photos/id/368/320/180.jpg?hmac=1SeoQS2Dc8M1rDUZucAzGDR3Pw1YWNpWEVfO6nliBuc")
//        videoRepository.save(v2)
    }

    private fun alreadyInitialized(): Boolean {
        val user = accountService.findAll()

        return user.isNotEmpty()
    }

    private fun createAdmin(): Account {
        val creation = AccountCreation(
            AccountRole.ADMIN,
            secretProperties.adminUsername,
            secretProperties.adminPassword,
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