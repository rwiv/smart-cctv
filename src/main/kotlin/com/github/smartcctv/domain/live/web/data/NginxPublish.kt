package com.github.smartcctv.domain.live.web.data

data class NginxPublish(
    val app: String,
    val flashver: String,
    val swfurl: String,
    val tcurl: String,
    val pageurl: String,
    val addr: String,
    val clientid: String,
    val call: String,
    val name: String, // stream key
    val type: String,
)
