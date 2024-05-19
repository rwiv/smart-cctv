package com.github.smartcctv.domain.video.business

import com.github.smartcctv.common.error.exception.NotFoundException
import com.github.smartcctv.domain.device.persistence.DeviceRepository
import com.github.smartcctv.domain.video.business.data.VideoSegmentCreation
import com.github.smartcctv.domain.video.peresistence.Video
import com.github.smartcctv.domain.video.peresistence.VideoRepository
import com.github.smartcctv.domain.video.peresistence.VideoSegment
import com.github.smartcctv.domain.video.peresistence.VideoSegmentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
class VideoService(
    private val videoRepository: VideoRepository,
    private val videoSegmentRepository: VideoSegmentRepository,
    private val deviceRepository: DeviceRepository,
) {

    fun findByDeviceId(deviceId: UUID): List<Video> {
        val device = deviceRepository.findByIdNotNull(deviceId)
        return videoRepository.findByDevice(device)
    }

    @Transactional
    fun createVideoSegment(creation: VideoSegmentCreation): VideoSegment {
        val video = deviceRepository.findByStreamKey(creation.streamKey)?.live?.video
            ?: throw NotFoundException("not found video by stream key")

        val latest = videoSegmentRepository.findLatestOne(video)
        val num = if (latest === null) { 0 } else { latest.num + 1 }

        val now = LocalDateTime.now()
        val tbcVidSeg = VideoSegment(video, creation.path, num, creation.size, now, now)
        return videoSegmentRepository.save(tbcVidSeg)
    }
}
