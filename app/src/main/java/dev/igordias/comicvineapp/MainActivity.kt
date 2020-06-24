package dev.igordias.comicvineapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.igordias.comicvineapp.core.data.inject.INJECT_COMPONENT_REPOSITORY
import dev.igordias.comicvineapp.core.domain.repository.MovieRepository
import dev.igordias.comicvineapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import org.rewedigital.katana.Component
import org.rewedigital.katana.KatanaTrait
import org.rewedigital.katana.Module
import org.rewedigital.katana.inject
import java.util.Collections.singleton


class MainActivity : AppCompatActivity(), KatanaTrait {
    private lateinit var binding: ActivityMainBinding

    override val component = Component(
        modules = listOf(createMainModule(this)),
        dependsOn = listOf(MyApp.applicationComponent, INJECT_COMPONENT_REPOSITORY)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setupNavigation() {
        //TODO: Create bottom nav
        //navController = Navigation.findNavController(this, R.id.main_navigation_fragment)
        //NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

    private fun createMainModule(context: Context) = Module {
        singleton { context }
    }
}
