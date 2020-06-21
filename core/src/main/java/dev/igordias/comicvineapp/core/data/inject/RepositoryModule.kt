package dev.igordias.comicvineapp.core.data.inject

import dev.igordias.comicvineapp.core.data.repository.MovieRepositoryImpl
import dev.igordias.comicvineapp.core.domain.repository.MovieRepository
import org.rewedigital.katana.Component
import org.rewedigital.katana.Module
import org.rewedigital.katana.dsl.factory

private enum class RepositoryModuleNamedBinding {
    MOVIES_REPOSITORY
}

val INJECT_MODULE_REPOSITORY = Module {
    factory<MovieRepository> { MovieRepositoryImpl() }
}
val INJECT_COMPONENT_REPOSITORY = Component(modules = listOf(INJECT_MODULE_REPOSITORY))