package com.github.smartcctv.domain.device.business.data

import java.util.*

data class IotDeviceCreation(
    val ownerId: UUID,
    val name: String,
)