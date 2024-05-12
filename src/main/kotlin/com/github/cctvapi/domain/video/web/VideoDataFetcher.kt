package com.github.cctvapi.domain.video.web

import com.github.cctvapi.domain.video.business.VideoService
import com.github.cctvapi.domain.video.peresistence.Video
import com.netflix.graphql.dgs.*
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@DgsComponent
class VideoDataFetcher(
    private val videoService: VideoService,
) {

    @DgsQuery
    fun videos(@InputArgument deviceId: UUID): List<Video> {
        return videoService.findByDeviceId(deviceId)
    }

    @DgsData(parentType = "Video")
    fun createdAt(dfe: DgsDataFetchingEnvironment): OffsetDateTime {
        val video = dfe.getSource<Video>()
        return video.createdAt.atOffset(ZoneOffset.UTC)
    }
}
