package com.github.smartcctv.mqtt

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.junit.jupiter.api.Test


class MqttMessageTest {

    @Test fun test_publish_message() {

        val topic = "detection"
        val url = "tcp://localhost:1883"
        val client = MqttClient(url, "aaa", null)
        client.connect()

        val message = MqttMessage()
        message.payload = "hello world".toByteArray()
        client.publish(topic, message)

        client.disconnect()
        client.close()
    }
}