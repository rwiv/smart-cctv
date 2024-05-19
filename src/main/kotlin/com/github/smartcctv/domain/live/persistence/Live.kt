package com.github.smartcctv.domain.live.persistence

import com.github.smartcctv.common.persistence.BaseEntity
import com.github.smartcctv.domain.device.persistence.Device
import com.github.smartcctv.domain.video.peresistence.Video
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "live")
class Live(

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    val device: Device,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = true)
    var video: Video?,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    var onLive: Boolean,

    @Column(nullable = false)
    val viewerCnt: Int,

    @Column(nullable = true)
    var startedAt: LocalDateTime? = null,

    @Column(nullable = true)
    var closedAt: LocalDateTime? = null,

    id: UUID? = null,
) : BaseEntity(id)
