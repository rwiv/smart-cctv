package com.github.smartcctv.domain.live.business

import com.github.smartcctv.common.error.exception.NotFoundException
import com.github.smartcctv.domain.device.persistence.Device
import com.github.smartcctv.domain.device.persistence.DeviceRepository
import com.github.smartcctv.domain.live.persistence.Live
import com.github.smartcctv.domain.live.persistence.LiveRepository
import com.github.smartcctv.domain.video.peresistence.Video
import com.github.smartcctv.domain.video.peresistence.VideoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class LiveService(
    private val liveRepository: LiveRepository,
    private val videoRepository: VideoRepository,
    private val deviceRepository: DeviceRepository,
) {

    @Transactional
    fun createLive(device: Device): Live {
        val now = LocalDateTime.now()
        val liveTitle = now.toString()
        val tbcLive = Live(device, null, liveTitle, true, 0, now)
        return liveRepository.save(tbcLive).also {
            device.live = it
        }
    }

    @Transactional
    fun onPublish(streamKey: String): Live {
        val live = deviceRepository.findByStreamKey(streamKey)?.live
            ?: throw NotFoundException("not found live by stream key")

        live.video = videoRepository.save(Video(live.device, live.title))
        live.onLive = true
        live.startedAt = LocalDateTime.now()
        liveRepository.save(live)

        return live
    }

    @Transactional
    fun onPublishDone(streamKey: String): Live? {
        val live = deviceRepository.findByStreamKey(streamKey)?.live
            ?: throw NotFoundException("not found live by stream key")

        live.video = null
        live.onLive = false
        live.closedAt = LocalDateTime.now()
        liveRepository.save(live)

        return live
    }
}
