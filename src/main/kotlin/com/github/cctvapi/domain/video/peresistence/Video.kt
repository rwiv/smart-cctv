package com.github.cctvapi.domain.video.peresistence

import com.github.cctvapi.common.persistence.BaseTimeEntity
import com.github.cctvapi.domain.device.persistence.IotDevice
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "video")
class Video(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iot_device_id", nullable = false)
    val device: IotDevice,

    @Column(nullable = false)
    val title: String,

    id: UUID? = null,
) : BaseTimeEntity(id)
