package com.github.smartcctv.domain.device.persistence

import com.github.smartcctv.domain.account.persistence.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface DeviceRepository : JpaRepository<Device, UUID>, DeviceRepositoryCustom {

    fun findByOwner(owner: Account): List<Device>
    fun findByStreamKey(streamKey: String): Device?
}
