package dev.igordias.comicvineapp.core.data.repository.base

import dev.igordias.comicvineapp.core.data.inject.INJECT_COMPONENT_REMOTE_CLIENT
import dev.igordias.comicvineapp.core.data.remote.RetrofitService
import dev.igordias.comicvineapp.core.data.remote.request.RequestHandler
import org.rewedigital.katana.Component
import org.rewedigital.katana.KatanaTrait
import org.rewedigital.katana.injectNow

abstract class BaseRepository : KatanaTrait, RequestHandler() {
    override val component = Component(dependsOn = listOf(INJECT_COMPONENT_REMOTE_CLIENT))
    protected val apiService = injectNow<RetrofitService>()
}