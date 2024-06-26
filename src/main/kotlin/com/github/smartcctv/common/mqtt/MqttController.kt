package com.github.smartcctv.common.mqtt

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/mqtt")
class MqttController(
    private val mqttService: MqttService,
) {

    @GetMapping("/subscribe")
    fun subscribe(
        @RequestParam url: String,
        @RequestParam topic: String,
    ): String {
        mqttService.subscribe(url, topic)
        println("subscribe complete!")
        return "subscribe complete!"
    }
}
