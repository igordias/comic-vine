package dev.igordias.comicvineapp.core.data.repository

import dev.igordias.comicvineapp.core.data.remote.entity.ApiMovie
import dev.igordias.comicvineapp.core.data.repository.base.BaseRepository
import dev.igordias.comicvineapp.core.domain.entity.Movie
import dev.igordias.comicvineapp.core.domain.repository.MovieRepository

class MovieRepositoryImpl : BaseRepository(), MovieRepository {

    override suspend fun getAllMovies(): List<Movie> {
        return apiService.getMovies(100, 1)
            .result()
            .map(ApiMovie.ToMovieMapper::transform)
    }

}