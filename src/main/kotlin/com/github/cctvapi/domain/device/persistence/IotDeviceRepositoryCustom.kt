package com.github.cctvapi.domain.device.persistence

import java.util.*

interface IotDeviceRepositoryCustom {

    fun findByIdNotNull(id: UUID): IotDevice
}