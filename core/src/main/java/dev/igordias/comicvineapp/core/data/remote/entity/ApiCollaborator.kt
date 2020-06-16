package dev.igordias.comicvineapp.core.data.remote.entity

import com.squareup.moshi.Json
import dev.igordias.comicvineapp.core.data.util.Mapper
import dev.igordias.comicvineapp.core.domain.entity.Collaborator

data class ApiCollaborator(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?
) {
    object ToCollaboratorMapper : Mapper<ApiCollaborator, Collaborator>() {
        override fun transform(t: ApiCollaborator): Collaborator {
            return Collaborator(
                id = t.id?.toLongOrNull() ?: 0,
                name = t.name ?: ""
            )
        }

    }
}