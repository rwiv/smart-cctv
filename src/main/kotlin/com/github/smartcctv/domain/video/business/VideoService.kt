package com.github.smartcctv.domain.video.business

import com.github.smartcctv.domain.device.persistence.IotDeviceRepository
import com.github.smartcctv.domain.video.peresistence.Video
import com.github.smartcctv.domain.video.peresistence.VideoRepository
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