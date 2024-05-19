package com.github.smartcctv.domain.video.web

import com.github.smartcctv.domain.video.business.VideoService
import com.github.smartcctv.domain.video.business.data.VideoSegmentCreation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/rest/vs")
class VideoSegmentController(
    private val videoService: VideoService,
) {

    @PostMapping
    fun createVideoSegment(@RequestBody creation: VideoSegmentCreation): ResponseEntity<Unit> {
        videoService.createVideoSegment(creation)
        return ResponseEntity.ok().build()
    }
}
