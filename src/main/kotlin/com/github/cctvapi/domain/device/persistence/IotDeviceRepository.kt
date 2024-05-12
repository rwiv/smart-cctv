package com.github.cctvapi.domain.device.persistence

import com.github.cctvapi.domain.account.persistence.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IotDeviceRepository : JpaRepository<IotDevice, UUID>, IotDeviceRepositoryCustom {

    fun findByOwner(owner: Account): List<IotDevice>
}
