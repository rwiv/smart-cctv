package com.github.smartcctv.domain.device.business

import com.github.smartcctv.domain.account.persistence.AccountRepository
import com.github.smartcctv.domain.device.business.data.IotDeviceCreation
import com.github.smartcctv.domain.device.event.OnCreateDevice
import com.github.smartcctv.domain.device.persistence.IotDevice
import com.github.smartcctv.domain.device.persistence.IotDeviceRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class IotDeviceService(
    private val accountRepository: AccountRepository,
    private val iotDeviceRepository: IotDeviceRepository,
    private val publisher: ApplicationEventPublisher,
) {

    fun create(creation: IotDeviceCreation, ownerId: UUID): IotDevice {
        val owner = accountRepository.findByIdNotNull(ownerId)
        val streamKey = UUID.randomUUID().toString()
        val tbcDevice = IotDevice(owner, creation.name, streamKey)
        return iotDeviceRepository.save(tbcDevice).also {
            publisher.publishEvent(OnCreateDevice(it))
        }
    }

    fun findByOwnerId(ownerId: UUID): List<IotDevice> {
        val owner = accountRepository.findByIdNotNull(ownerId)
        return iotDeviceRepository.findByOwner(owner)
    }
}