package com.github.smartcctv.domain.device.event

import com.github.smartcctv.domain.device.persistence.Device

data class OnCreateDevice(
    val device: Device,
)
