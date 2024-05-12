package com.github.cctvapi.domain.video.peresistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface VideoSegmentRepository : JpaRepository<VideoSegment, UUID> {

    fun findByVideo(video: Video): List<VideoSegment>
}
