package com.github.smartcctv.domain.device.persistence

import com.github.smartcctv.utils.error.exception.NotFoundException
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
class DeviceRepositoryCustomImpl(em: EntityManager) : DeviceRepositoryCustom {

    private val repo = SimpleJpaRepository<Device, UUID>(Device::class.java, em)

    override fun findByIdNotNull(id: UUID): Device {
        return repo.findById(id).getOrNull()
            ?: throw NotFoundException("not found device")
    }
}
