package com.github.smartcctv.domain.live.web

import com.github.smartcctv.domain.live.business.LiveService
import com.github.smartcctv.domain.live.web.data.NginxPublish
import com.github.smartcctv.domain.live.web.data.NginxPublishDone
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/live")
class LiveController(
    private val liveService: LiveService,
) {

    @PostMapping("/publish")
    fun publish(@ModelAttribute req: NginxPublish): ResponseEntity<Unit> {
        liveService.onPublish(req.name)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/publish_done")
    fun publishDone(@ModelAttribute req: NginxPublishDone): ResponseEntity<Unit> {
        liveService.onPublishDone(req.name)
        return ResponseEntity.ok().build()
    }
}