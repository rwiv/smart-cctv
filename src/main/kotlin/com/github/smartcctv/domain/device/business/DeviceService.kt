package com.github.smartcctv.domain.device.business

import com.github.smartcctv.domain.account.persistence.AccountRepository
import com.github.smartcctv.domain.device.business.data.DeviceCreation
import com.github.smartcctv.domain.device.event.OnCreateDevice
import com.github.smartcctv.domain.device.persistence.Device
import com.github.smartcctv.domain.device.persistence.DeviceRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeviceService(
    private val accountRepository: AccountRepository,
    private val deviceRepository: DeviceRepository,
    private val publisher: ApplicationEventPublisher,
) {

    @Transactional
    fun create(creation: DeviceCreation, ownerId: UUID): Device {
        val owner = accountRepository.findByIdNotNull(ownerId)
        val streamKey = UUID.randomUUID().toString()
        val tbcDevice = Device(owner, creation.name, streamKey)
        return deviceRepository.save(tbcDevice).also {
            publisher.publishEvent(OnCreateDevice(it))
        }
    }

    fun findByOwnerId(ownerId: UUID): List<Device> {
        val owner = accountRepository.findByIdNotNull(ownerId)
        return deviceRepository.findByOwner(owner)
    }
}
