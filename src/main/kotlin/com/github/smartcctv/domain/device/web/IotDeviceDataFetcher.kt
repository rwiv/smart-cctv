package com.github.smartcctv.domain.device.web

import com.github.smartcctv.domain.account.business.data.AccountResponse
import com.github.smartcctv.domain.device.business.IotDeviceService
import com.github.smartcctv.domain.device.business.data.IotDeviceCreation
import com.github.smartcctv.domain.device.persistence.IotDevice
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.springframework.security.core.Authentication
import java.util.UUID

@DgsComponent
class IotDeviceDataFetcher(
    private val iotDeviceService: IotDeviceService,
) {

    @DgsQuery
    fun iotDevices(@InputArgument ownerId: UUID): List<IotDevice> {
        return iotDeviceService.findByOwnerId(ownerId)
    }

    @DgsMutation
    fun createIotDevice(creation: IotDeviceCreation, authentication: Authentication): IotDevice {
        val accountResponse = authentication.details as AccountResponse
        return iotDeviceService.create(creation, UUID.fromString(accountResponse.id))
    }
}