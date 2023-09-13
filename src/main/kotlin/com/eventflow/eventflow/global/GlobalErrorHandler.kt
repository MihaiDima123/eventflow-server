package com.eventflow.eventflow.global

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class GlobalErrorHandler {
    val logger: Logger = LoggerFactory.getLogger("GlobalErrorHandler")
    @ExceptionHandler(Throwable::class)
    fun handleException(e: Throwable) {
        logger.error("error ", e)
    }
}