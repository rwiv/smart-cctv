package com.github.smartcctv.mqtt

import org.eclipse.paho.client.mqttv3.*


class MqttSubscriber : MqttCallback {

    fun subscribe() {
        val mqttOptions = MqttConnectOptions()
        mqttOptions.isCleanSession = true
        mqttOptions.keepAliveInterval = 30

        val mqttClient = MqttClient("tcp://localhost:1883", "bbb")

        mqttClient.setCallback(this)
        mqttClient.connect(mqttOptions)
        // 0: 1회 전송, 유실될 수 있음
        // 1: 받을 때 까지 전송, n회 전송될 수 있음
        // 2: 1회 전송 보장, n회 전송 x
        mqttClient.subscribe("iot", 0)
    }

    override fun connectionLost(throwable: Throwable) {
        println("connection lost")
        throwable.printStackTrace()
    }

    override fun messageArrived(topic: String, message: MqttMessage) {
        println("topic: $topic")
        val msg = String(message.payload)
        println("message: $msg")
    }

    override fun deliveryComplete(token: IMqttDeliveryToken) {
        println("token: $token")
    }
}