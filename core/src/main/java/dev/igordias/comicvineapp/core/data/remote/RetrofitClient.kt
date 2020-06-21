package dev.igordias.comicvineapp.core.data.remote

import dev.igordias.comicvineapp.core.data.inject.INJECT_COMPONENT_REMOTE_CLIENT
import dev.igordias.comicvineapp.core.data.remote.entity.ApiMovie
import dev.igordias.comicvineapp.core.data.remote.request.RequestHandler
import org.rewedigital.katana.Component
import org.rewedigital.katana.KatanaTrait
import org.rewedigital.katana.injectNow

class RetrofitClient : KatanaTrait, RequestHandler() {
    override val component = Component(dependsOn = listOf(INJECT_COMPONENT_REMOTE_CLIENT))

    private val service = injectNow<RetrofitService>()

    suspend fun getMovies(): List<ApiMovie> {
        return service.getMovies(100, 0).handle()
    }
}