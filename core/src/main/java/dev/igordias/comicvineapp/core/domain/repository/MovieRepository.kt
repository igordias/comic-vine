package dev.igordias.comicvineapp.core.domain.repository

import dev.igordias.comicvineapp.core.domain.entity.Movie

interface MovieRepository {
    suspend fun getAllMovies(): List<Movie>
}