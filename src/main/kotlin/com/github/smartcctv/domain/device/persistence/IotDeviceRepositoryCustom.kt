package com.github.smartcctv.domain.device.persistence

import java.util.*

interface IotDeviceRepositoryCustom {

    fun findByIdNotNull(id: UUID): IotDevice
}