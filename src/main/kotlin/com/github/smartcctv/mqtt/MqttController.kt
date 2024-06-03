package com.github.smartcctv.mqtt

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/mqtt")
class MqttController(
    private val mqttSubscribeManager: MqttSubscribeManager,
) {

    @GetMapping("/subscribe")
    fun subscribe(
        @RequestParam url: String,
        @RequestParam topic: String,
    ): String {
        mqttSubscribeManager.subscribe(url, topic)
        println("subscribe complete!")
        return "subscribe complete!"
    }
}
