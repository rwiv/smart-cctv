package com.github.cctvapi.domain.device.web

import com.github.cctvapi.domain.account.persistence.Account
import com.github.cctvapi.domain.device.business.IotDeviceService
import com.github.cctvapi.domain.device.business.data.IotDeviceCreation
import com.github.cctvapi.domain.device.persistence.IotDevice
import com.github.cctvapi.domain.device.persistence.IotDeviceRepository
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
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
    fun createIotDevice(creation: IotDeviceCreation): IotDevice {
        return iotDeviceService.create(creation)
    }
}