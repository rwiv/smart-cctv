package com.github.smartcctv.domain.video.web

import com.github.smartcctv.domain.video.business.VideoService
import com.github.smartcctv.domain.video.peresistence.Video
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
