package com.github.cctvapi.domain.device.persistence

import com.github.cctvapi.common.persistence.BaseTimeEntity
import com.github.cctvapi.domain.video.peresistence.Video
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "live")
class Live(

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iot_device_id", nullable = false)
    val device: IotDevice,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    val video: Video,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val viewerCnt: Int,

    id: UUID? = null,
) : BaseTimeEntity(id)
