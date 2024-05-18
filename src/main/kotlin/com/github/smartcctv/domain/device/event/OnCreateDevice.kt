package com.github.smartcctv.domain.device.event

import com.github.smartcctv.domain.device.persistence.IotDevice

data class OnCreateDevice(
    val device: IotDevice,
)
