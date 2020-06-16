package dev.igordias.comicvineapp.core.data.remote.request

enum class HttpErrorType {
    NOT_FOUND,
    TIMEOUT,
    INTERNAL_SERVER_ERROR,
    UNEXPECTED_ERROR;

    companion object {
        fun fromCode(code: Int): HttpErrorType {
            return when (code) {
                404 -> NOT_FOUND
                408 -> TIMEOUT
                500 -> INTERNAL_SERVER_ERROR
                else -> UNEXPECTED_ERROR
            }
        }
    }

}