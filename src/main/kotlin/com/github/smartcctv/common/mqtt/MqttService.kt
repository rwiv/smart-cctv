package com.github.smartcctv.common.mqtt

import org.springframework.stereotype.Service

@Service
class MqttService {

    fun subscribe(url: String, topic: String) {
        MqttSubscriber().subscribe(url, topic)
    }
}