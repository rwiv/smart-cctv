package com.github.smartcctv.utils.error.exception

open class MustCatchException(
    message: String? = null,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
