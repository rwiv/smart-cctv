package com.github.cctvapi.domain.video.peresistence

import java.util.*

interface VideoRepositoryCustom {

    fun findByIdNotNull(id: UUID): Video
}