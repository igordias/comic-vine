package dev.igordias.comicvineapp.core.data.remote.request.envelope

import com.squareup.moshi.Json

data class ResponseEnvelope<T>(
    @Json(name = "status_code") val statusCode: Int = HTTP_CODE_UNKNOWN,
    @Json(name = "error") val errorMessage: String = "",
    @Json(name = "number_of_total_results") val countResults: Int = 0,
    @Json(name = "number_of_page_results") val countPages: Int = 0,
    @Json(name = "limit") val limit: Int = 100,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "results") val results: T
) {
    // The Comic Vine Api returns 1 as a code for successful responses
    val isSuccessful = when (statusCode) {
        1, 200 -> true
        else -> false
    }

    companion object {
        // 418 = "This code should be returned by teapots requested to brew coffee"
        // Used here to state that the response didn't include an error code
        const val HTTP_CODE_UNKNOWN = 418
    }
}