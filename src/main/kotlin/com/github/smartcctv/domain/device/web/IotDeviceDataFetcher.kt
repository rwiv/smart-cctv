package com.github.smartcctv.domain.device.web

import com.github.smartcctv.domain.account.business.data.AccountResponse
import com.github.smartcctv.domain.device.business.DeviceService
import com.github.smartcctv.domain.device.business.data.DeviceCreation
import com.github.smartcctv.domain.device.persistence.Device
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.springframework.security.core.Authentication
import java.util.UUID

@DgsComponent
class IotDeviceDataFetcher(
    private val deviceService: DeviceService,
) {

    @DgsQuery
    fun iotDevices(@InputArgument ownerId: UUID): List<Device> {
        return deviceService.findByOwnerId(ownerId)
    }

    @DgsMutation
    fun createIotDevice(creation: DeviceCreation, authentication: Authentication): Device {
        val accountResponse = authentication.details as AccountResponse
        return deviceService.create(creation, UUID.fromString(accountResponse.id))
    }
}