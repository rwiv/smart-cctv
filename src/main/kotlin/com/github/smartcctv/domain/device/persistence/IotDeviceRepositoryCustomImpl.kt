package com.github.smartcctv.domain.device.persistence

import com.github.smartcctv.common.error.exception.NotFoundException
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class IotDeviceRepositoryCustomImpl(em: EntityManager) : IotDeviceRepositoryCustom {

    private val repo = SimpleJpaRepository<IotDevice, UUID>(IotDevice::class.java, em)

    override fun findByIdNotNull(id: UUID): IotDevice {
        return repo.findById(id).getOrNull()
            ?: throw NotFoundException("not found iotDevice")
    }
}
