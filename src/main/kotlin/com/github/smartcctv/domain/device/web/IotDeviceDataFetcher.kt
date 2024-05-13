package com.github.smartcctv.domain.device.web

import com.github.smartcctv.domain.device.business.IotDeviceService
import com.github.smartcctv.domain.device.business.data.IotDeviceCreation
import com.github.smartcctv.domain.device.persistence.IotDevice
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