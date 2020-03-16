package com.dsm.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.dsm.base.ui.PageConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(supportFragmentManager.findFragmentById(R.id.fragment_root_nav_host) as NavHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tb_main)

        setupToolbar()
    }

    private fun setupToolbar() {
        tb_main.setNavigationOnClickListener { onBackPressed() }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.mainFragment) {
                tb_main.visibility = View.GONE
                return@addOnDestinationChangedListener
            }
            changeToolbarByDestination(destination)
        }
    }

    internal fun changeToolbarByDestination(destination: NavDestination) {
        val config = PageConfiguration.getConfiguration(destination.id)

        tb_main.visibility =
            if (config.hideToolbar) View.GONE
            else View.VISIBLE

        tb_main.navigationIcon =
            if (!config.isTopLevel) AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back_black_24dp)
            else null

        tv_title.text = destination.label
    }
}
