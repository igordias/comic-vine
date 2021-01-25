package dev.igordias.comicvineapp.core.data.remote.entity

import com.squareup.moshi.Json
import dev.igordias.comicvineapp.core.data.util.Mapper
import dev.igordias.comicvineapp.core.domain.entity.MovieImage

data class ApiMovieImage(
    @Json(name = "icon_url") val icon: String,
    @Json(name = "medium_url") val medium: String,
    @Json(name = "screen_url") val screen: String,
    @Json(name = "screen_large_url") val screenLarge: String,
    @Json(name = "small_url") val small: String,
    @Json(name = "super_url") val superUrl: String,
    @Json(name = "thumb_url") val thumbnail: String,
    @Json(name = "tiny_url") val tiny: String,
    @Json(name = "original_url") val original: String,
    @Json(name = "image_tags") val tags: String
) {
    object ToMovieImageMapper : Mapper<ApiMovieImage?, MovieImage>() {
        override fun transform(t: ApiMovieImage?): MovieImage {
            return MovieImage(
                icon = t?.icon ?: "",
                medium = t?.medium ?: "",
                screen = t?.screen ?: "",
                screenLarge = t?.screenLarge ?: "",
                small = t?.small ?: "",
                superUrl = t?.superUrl ?: "",
                thumbnail = t?.thumbnail ?: "",
                tiny = t?.tiny ?: "",
                original = t?.original ?: "",
                tags = t?.tags ?: ""
            )
        }

    }
}