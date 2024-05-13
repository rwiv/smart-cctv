package com.github.smartcctv.common.error.exception

open class MustCatchException(
    message: String? = null,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
