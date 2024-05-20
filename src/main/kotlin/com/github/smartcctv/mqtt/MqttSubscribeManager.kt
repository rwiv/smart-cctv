package com.github.smartcctv.mqtt

import org.springframework.stereotype.Component

@Component
class MqttSubscribeManager {

    init {
        MqttSubscriber().subscribe()
    }
}