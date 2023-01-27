package com.fonagyma.vlsg

/**Készítette: Fónagy Márton Ádám
 * Visszamentél az időben.Chernobylban találod magadat.
 * A reaktor 5 perc múlva eléri a kritikus pontot ahonnan nincs visszaút.
 * Habár ismered a jövőt, nem tudod figyelmeztetni a kezelőket, nincs se nyelvtudásod se időd.
 * De még van esély megmenteni magad és a környéket.
 * Egy titkos grafikus kulcssorozattal leállíthatod egyedül is a reaktort.
 *
 * Kövesd a felvillanó negyzeteket.Ismételd meg a gép utasításait. Az idő jelzi mikor utheted be a kódokat. De vigyázz, csak 3 szor hibázhatsz.
 */

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import com.fonagyma.vlsg.R
import com.fonagyma.vlsg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}