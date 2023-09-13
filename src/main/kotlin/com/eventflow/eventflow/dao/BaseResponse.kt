package com.eventflow.eventflow.dao

class BaseResponse (
    var error: Boolean = false,
    var message: String = "",
) {
    companion object {
        fun ofMessage(message: String): BaseResponse {
            val response = BaseResponse()
            response.message = message
            return response
        }

        fun ofError(): BaseResponse {
            return ofError("error")
        }

        fun ofError(message: String): BaseResponse {
            val response = BaseResponse()
            response.error = true
            response.message = message
            return response
        }

        fun ofBadRequest(): BaseResponse {
            return ofBadRequest("bad request")
        }

        fun ofBadRequest(message: String): BaseResponse {
            val response = BaseResponse()
            response.error = true
            response.message = message
            return response
        }
    }
}