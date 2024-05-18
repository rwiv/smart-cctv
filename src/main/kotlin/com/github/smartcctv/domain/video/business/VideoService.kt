package com.github.smartcctv.domain.video.business

import com.github.smartcctv.domain.device.persistence.DeviceRepository
import com.github.smartcctv.domain.video.peresistence.Video
import com.github.smartcctv.domain.video.peresistence.VideoRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class VideoService(
    private val videoRepository: VideoRepository,
    private val deviceRepository: DeviceRepository,
) {

    fun findByDeviceId(deviceId: UUID): List<Video> {
        val device = deviceRepository.findByIdNotNull(deviceId)
        return videoRepository.findByDevice(device)
    }
}