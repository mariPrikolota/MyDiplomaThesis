package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.navigation_layout.*

class NavigationActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val gameFragment = GameFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_layout)

        configureNavigationBar()
    }

    private fun configureNavigationBar() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, homeFragment).commit()

        navigationView.setOnItemSelectedListener { item ->
            var selectFragment : Fragment? = null

            when (item.itemId) {
                R.id.home -> selectFragment = homeFragment
                R.id.game -> selectFragment = gameFragment
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, selectFragment!!).commit()
            true
        }
    }

}