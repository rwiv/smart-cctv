package com.github.smartcctv.domain.video.peresistence

import com.github.smartcctv.common.persistence.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "video_segment")
class VideoSegment(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    val video: Video,

    @Column(nullable = false)
    val filename: String,

    @Column(nullable = false, unique = true)
    val path: String,

    @Column(nullable = false)
    val num: Int,

    @Column(nullable = false)
    val size: Long,

    @Column(nullable = false, updatable = false)
    var ctime: LocalDateTime,

    @Column(nullable = false)
    var mtime: LocalDateTime,

    id: UUID? = null,
) : BaseTimeEntity(id)
