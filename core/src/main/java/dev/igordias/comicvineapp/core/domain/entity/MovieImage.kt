package dev.igordias.comicvineapp.core.domain.entity

import java.io.Serializable

data class MovieImage(
    val icon: String,
    val medium: String,
    val screen: String,
    val screenLarge: String,
    val small: String,
    val superUrl: String,
    val thumbnail: String,
    val tiny: String,
    val original: String,
    val tags: String
) : Serializable