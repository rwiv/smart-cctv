package com.github.smartcctv.mqtt

import org.springframework.stereotype.Component

@Component
class MqttSubscribeManager {

    fun subscribe(url: String, topic: String) {
        MqttSubscriber().subscribe(url, topic)
    }
}