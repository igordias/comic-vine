package dev.igordias.comicvineapp.core.data.remote.request.envelope

import com.squareup.moshi.Json

data class ResponseEnvelope<T>(
    @Json(name = "status_code") val statusCode: Int = 0,
    @Json(name = "error") val errorMessage: String = "",
    @Json(name = "number_of_total_results") val countResults: Int = 0,
    @Json(name = "number_of_page_results") val countPages: Int = 0,
    @Json(name = "limit") val limit: Int = 100,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "results") val results: T
)