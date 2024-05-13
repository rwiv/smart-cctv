package com.github.smartcctv.domain.device.business

import com.github.smartcctv.domain.account.persistence.AccountRepository
import com.github.smartcctv.domain.device.business.data.IotDeviceCreation
import com.github.smartcctv.domain.device.persistence.IotDevice
import com.github.smartcctv.domain.device.persistence.IotDeviceRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class IotDeviceService(
    private val accountRepository: AccountRepository,
    private val iotDeviceRepository: IotDeviceRepository,
) {

    fun create(creation: IotDeviceCreation): IotDevice {
        val owner = accountRepository.findByIdNotNull(creation.ownerId)
        return iotDeviceRepository.save(IotDevice(owner, creation.name))
    }

    fun findByOwnerId(ownerId: UUID): List<IotDevice> {
        val owner = accountRepository.findByIdNotNull(ownerId)
        return iotDeviceRepository.findByOwner(owner)
    }
}