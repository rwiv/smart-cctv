package com.github.smartcctv.common.error.exception

class NotFoundException(
    message: String,
   cause: Throwable? = null,
) : HttpException(404, message, cause)
