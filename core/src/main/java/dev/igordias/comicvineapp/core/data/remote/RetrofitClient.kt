package dev.igordias.comicvineapp.core.data.remote

import dev.igordias.comicvineapp.core.data.inject.remoteComponent
import dev.igordias.comicvineapp.core.data.remote.entity.ApiMovie
import org.rewedigital.katana.Component
import org.rewedigital.katana.KatanaTrait
import org.rewedigital.katana.injectNow

class RetrofitClient : KatanaTrait {
    override val component = Component(dependsOn = listOf(remoteComponent))

    private val service = injectNow<RetrofitService>()

    suspend fun getMovies(): List<ApiMovie> {
        //TODO: Stop screaming!! and treat return properly
        return service.getMovies(0, 1).body()!!
    }
}