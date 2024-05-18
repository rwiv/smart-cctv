package com.github.smartcctv.domain.live.event

import com.github.smartcctv.domain.device.event.OnCreateDevice
import com.github.smartcctv.domain.live.business.LiveService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class LiveSubscriber(
    private val liveService: LiveService,
) {

    @EventListener
    fun onCreateDevice(event: OnCreateDevice) {
        liveService.createLive(event.device)
    }
}
