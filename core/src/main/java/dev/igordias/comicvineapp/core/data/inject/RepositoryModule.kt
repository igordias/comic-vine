package dev.igordias.comicvineapp.core.data.inject

import dev.igordias.comicvineapp.core.data.repository.MovieRepositoryImpl
import dev.igordias.comicvineapp.core.domain.repository.MovieRepository
import org.rewedigital.katana.Component
import org.rewedigital.katana.Module
import org.rewedigital.katana.dsl.factory

val INJECT_MODULE_REPOSITORY = Module {
    factory<MovieRepository> { MovieRepositoryImpl() }
}
val INJECT_COMPONENT_REPOSITORY = Component(modules = listOf(INJECT_MODULE_REPOSITORY))