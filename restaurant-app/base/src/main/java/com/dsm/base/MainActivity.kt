package com.dsm.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dsm.base.ui.PageConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnLayoutChangeListener {

    private val fragment: View by lazy { findViewById<View>(R.id.fragment_root_nav_host) }

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(supportFragmentManager.findFragmentById(R.id.fragment_root_nav_host) as NavHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
    }

    private fun setupToolbar() {
        tb_main.setNavigationOnClickListener {
            onBackPressed()
        }

        navController.addOnDestinationChangedListener { _, _, _ ->
            fragment.addOnLayoutChangeListener(this)
        }
    }

    override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
        val destination = navController.currentDestination!!
        val config = PageConfiguration.getConfiguration(destination.id)

        tb_main.visibility =
            if (config.hideToolbar) View.INVISIBLE
            else View.VISIBLE

        tb_main.navigationIcon =
            if (!config.isTopLevel) AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back_black_24dp)
            else null

        tv_title.text = destination.label

        fragment.removeOnLayoutChangeListener(this)
    }
}
