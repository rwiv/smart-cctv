package com.github.smartcctv.domain.video.business.data

data class VideoSegmentCreation(
    val streamKey: String,
    val path: String,
    val size: Long,
)
