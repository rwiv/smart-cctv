package com.github.cctvapi.domain.video.business

import com.github.cctvapi.domain.device.persistence.IotDeviceRepository
import com.github.cctvapi.domain.video.peresistence.Video
import com.github.cctvapi.domain.video.peresistence.VideoRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class VideoService(
    private val videoRepository: VideoRepository,
    private val iotDeviceRepository: IotDeviceRepository,
) {

    fun findByDeviceId(deviceId: UUID): List<Video> {
        val device = iotDeviceRepository.findByIdNotNull(deviceId)
        return videoRepository.findByDevice(device)
    }
}