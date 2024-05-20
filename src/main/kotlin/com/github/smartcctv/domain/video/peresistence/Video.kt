package com.github.smartcctv.domain.video.peresistence

import com.github.smartcctv.utils.persistence.BaseTimeEntity
import com.github.smartcctv.domain.device.persistence.Device
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "video")
class Video(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    val device: Device,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = true)
    var thumbnailUrl: String? = null,

    id: UUID? = null,
) : BaseTimeEntity(id)
