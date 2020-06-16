package dev.igordias.comicvineapp.core.data.remote

import dev.igordias.comicvineapp.core.data.remote.entity.ApiMovie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("movies")
    suspend fun getMovies(
        @Query("limit") limit: Int,
        @Query("offset") offset: Long
    ): Response<List<ApiMovie>>
}