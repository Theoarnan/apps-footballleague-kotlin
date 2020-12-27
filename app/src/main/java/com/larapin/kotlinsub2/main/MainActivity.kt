package com.larapin.kotlinsub2.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.widget.Button
import android.widget.FrameLayout
import com.larapin.kotlinsub2.event.EventFragment
import com.larapin.kotlinsub2.R
import com.larapin.kotlinsub2.league.LeagueFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = LeagueFragment.newInstance("")
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .commit()
    }
}
