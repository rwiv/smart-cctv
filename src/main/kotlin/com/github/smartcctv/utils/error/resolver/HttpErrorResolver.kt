package com.github.smartcctv.utils.error.resolver

import com.github.smartcctv.utils.error.data.HttpError
import com.github.smartcctv.utils.error.exception.HttpException
import com.github.smartcctv.utils.error.exception.MustCatchException
import com.github.smartcctv.utils.error.exception.UncaughtException
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class HttpErrorResolver {

    private val logger = KotlinLogging.logger {}

    fun toHttpError(throwable: Throwable, req: HttpServletRequest): HttpError {
        val httpException = resolve(throwable)
        return HttpError(
            uuid = UUID.randomUUID().toString(),
            timestamp = LocalDateTime.now(),
            exception = httpException,
            req = req,
        )
    }

    fun resolve(throwable: Throwable): HttpException {
        return when (throwable) {
            is HttpException -> throwable
            is MustCatchException -> UncaughtException(500, throwable)
            else -> wrapDefaultHttpException(throwable)
        }
    }

    fun printErrorInfo(err: HttpError) {
        if (err.errors != null){
            logger.debug { "errors: " + err.errors }
        }
        logger.debug(err.exception) { "response this error:" }
    }

    private fun wrapDefaultHttpException(throwable: Throwable): HttpException {
        return HttpException(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = throwable.message,
            cause = throwable
        )
    }
}