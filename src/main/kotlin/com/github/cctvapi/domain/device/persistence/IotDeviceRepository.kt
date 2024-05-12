package com.github.cctvapi.domain.device.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IotDeviceRepository : JpaRepository<IotDevice, UUID> {
}