package com.github.smartcctv.domain.device.persistence

import java.util.*

interface DeviceRepositoryCustom {

    fun findByIdNotNull(id: UUID): Device
}