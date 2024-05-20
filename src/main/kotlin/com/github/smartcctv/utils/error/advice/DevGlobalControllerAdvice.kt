package com.github.smartcctv.utils.error.advice

import com.github.smartcctv.utils.error.data.HttpErrorResponse
import com.github.smartcctv.utils.error.resolver.HttpErrorResolver
import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Profile("dev")
@RestControllerAdvice
class DevGlobalControllerAdvice(val resolver: HttpErrorResolver) {

    @ExceptionHandler
    fun handle(throwable: Throwable, req: HttpServletRequest): ResponseEntity<HttpErrorResponse> {
        val err = resolver.toHttpError(throwable, req)
        resolver.printErrorInfo(err)
        return err.toResponseEntity()
    }
}