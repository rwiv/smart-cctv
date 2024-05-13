package com.github.smartcctv.common.error.exception

class UncaughtException(
    status: Int,
    cause: MustCatchException,
    message: String? = "uncaught ${cause.javaClass.simpleName}",
) : HttpException(status, message, cause)
