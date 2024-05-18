package com.github.smartcctv.domain.live.business

import com.github.smartcctv.domain.device.persistence.IotDevice
import com.github.smartcctv.domain.device.persistence.IotDeviceRepository
import com.github.smartcctv.domain.live.persistence.Live
import com.github.smartcctv.domain.live.persistence.LiveRepository
import com.github.smartcctv.domain.video.peresistence.Video
import com.github.smartcctv.domain.video.peresistence.VideoRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LiveService(
    private val liveRepository: LiveRepository,
    private val videoRepository: VideoRepository,
    private val iotDeviceRepository: IotDeviceRepository,
) {

    fun createInitLive(device: IotDevice): Live {
        val videos = videoRepository.findByDevice(device)

        val title = "${device.id}_${videos.size}"
        val tbcVideo = Video(device, title)
        val video = videoRepository.save(tbcVideo)

        val tbcLive = Live(device, video, title, false, 0)
        return liveRepository.save(tbcLive).also {
            device.updateLive(it)
        }
    }

    fun onPublish(streamKey: String) {
        iotDeviceRepository.findByStreamKey(streamKey)?.live?.also {
            it.onLive = true
            it.startedAt = LocalDateTime.now()
            liveRepository.save(it)
        }
    }

    fun onPublishDone(streamKey: String) {
        iotDeviceRepository.findByStreamKey(streamKey)?.live?.also {
            it.onLive = false
            it.closedAt = LocalDateTime.now()
            liveRepository.save(it)
        }
    }
}
