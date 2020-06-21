package dev.igordias.comicvineapp.core.data.remote

import dev.igordias.comicvineapp.core.data.remote.entity.ApiMovie
import dev.igordias.comicvineapp.core.domain.util.RequestException
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import java.net.SocketTimeoutException

interface RetrofitService {
    @GET("movies")
    suspend fun getMovies(
        @Query("limit") limit: Int,
        @Query("offset") offset: Long
    ): Response<List<ApiMovie>>
}