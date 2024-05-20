package com.github.smartcctv.mqtt

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.junit.jupiter.api.Test


class MqttMessageTest {

    @Test fun test_publish_message() {
        val client = MqttClient("tcp://localhost:1883", "aaa")
        client.connect()

        val message = MqttMessage()
        message.payload = "hello world".toByteArray()
        client.publish("iot", message)

        client.disconnect()
        client.close()
    }
}