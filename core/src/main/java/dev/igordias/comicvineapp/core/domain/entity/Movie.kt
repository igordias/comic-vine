package dev.igordias.comicvineapp.core.domain.entity

import java.io.Serializable

data class Movie(
    val id: Long,
    val name: String,
    val producers: List<Collaborator>,
    val writers: List<Collaborator>,
    val totalRevenue: String
) : Serializable