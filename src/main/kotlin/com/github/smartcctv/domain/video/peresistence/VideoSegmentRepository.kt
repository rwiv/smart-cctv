package com.github.smartcctv.domain.video.peresistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface VideoSegmentRepository : JpaRepository<VideoSegment, UUID> {

    fun findByVideo(video: Video): List<VideoSegment>

    @Query("SELECT vs FROM VideoSegment vs " +
            "WHERE vs.video = :video " +
            "ORDER BY vs.num DESC LIMIT 1")
    fun findLatestOne(video: Video): VideoSegment?
}
