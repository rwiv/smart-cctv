package com.github.cctvapi.domain.device.business

import com.github.cctvapi.domain.device.business.data.LiveCreation
import com.github.cctvapi.domain.device.persistence.IotDeviceRepository
import com.github.cctvapi.domain.device.persistence.Live
import com.github.cctvapi.domain.device.persistence.LiveRepository
import com.github.cctvapi.domain.video.peresistence.Video
import com.github.cctvapi.domain.video.peresistence.VideoRepository
import org.springframework.stereotype.Service

@Service
class LiveService(
    private val liveRepository: LiveRepository,
    private val videoRepository: VideoRepository,
    private val iotDeviceRepository: IotDeviceRepository,
) {

    fun create(creation: LiveCreation): Live {
        val device = iotDeviceRepository.findByIdNotNull(creation.deviceId)

        val tbcVideo = Video(device, creation.liveTitle)
        val video = videoRepository.save(tbcVideo)

        val tbcLive = Live(device, video, creation.liveTitle, 0)
        return liveRepository.save(tbcLive)
    }
}
