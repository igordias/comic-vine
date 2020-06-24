package dev.igordias.comicvineapp

import android.app.Application
import org.rewedigital.katana.Component
import org.rewedigital.katana.Katana
import org.rewedigital.katana.android.environment.AndroidEnvironmentContext
import org.rewedigital.katana.android.modules.ApplicationModule


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKatana()
    }

    private fun setupKatana() {
        Katana.environmentContext = AndroidEnvironmentContext()
        applicationComponent = Component(ApplicationModule(this))
    }

    companion object {
        lateinit var applicationComponent: Component
    }
}