package dev.igordias.comicvineapp.core.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status_code") val statusCode: String = "0",
    @SerializedName("error") val errorMessage: String = "",
    @SerializedName("number_of_total_results") val countResults: String = "0",
    @SerializedName("number_of_page_results") val countPages: String = "0",
    @SerializedName("limit") val limit: String = "100",
    @SerializedName("offset") val offset: String = "0",
    @SerializedName("results") val results: String = "0"
)