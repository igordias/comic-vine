package dev.igordias.comicvineapp.core.data.remote.entity

import com.squareup.moshi.Json
import dev.igordias.comicvineapp.core.data.util.Mapper
import dev.igordias.comicvineapp.core.domain.entity.Movie

data class ApiMovie(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "producers") val producers: List<ApiCollaborator>?,
    @Json(name = "writers") val writers: List<ApiCollaborator>?,
    @Json(name = "total_revenue") val totalRevenue: String?
) {
    object ToMovieMapper : Mapper<ApiMovie, Movie>() {
        override fun transform(t: ApiMovie): Movie {
            return Movie(
                id = t.id?.toLongOrNull() ?: 0,
                name = t.name ?: "",
                producers = mapCollaborators(t.producers) ?: emptyList(),
                writers = mapCollaborators(t.writers) ?: emptyList(),
                totalRevenue = t.totalRevenue ?: ""
            )
        }

        private fun mapCollaborators(collaborators: List<ApiCollaborator>?) =
            ApiCollaborator.ToCollaboratorMapper.transform(collaborators)


    }
}