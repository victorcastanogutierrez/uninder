package com.uninder.uninder

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.uninder.uninder.findPeople.FindPeopleFragment
import com.uninder.uninder.getMatches.MatchesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FindPeopleFragment.OnFindPeopleInteractionListener,
        MatchesFragment.OnGetMatchesInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initialize()
    }

    private fun initialize() {
        navigation.selectedItemId = R.id.menu_search
        onSelectedMenuBottom()
        supportFragmentManager.beginTransaction().add(mainContainer.id, FindPeopleFragment()).commit()

    }

    private fun onSelectedMenuBottom() {


        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_matches -> changeFragment(MatchesFragment(), it)
                R.id.menu_search -> changeFragment(FindPeopleFragment(), it)
                R.id.menu_account -> changeFragment(null!!, it)
                else -> changeFragment(FindPeopleFragment(), it)
            }
        }
    }

    private fun changeFragment(fragment: Fragment, menuItem: MenuItem): Boolean {
        menuItem.isEnabled = true
        supportFragmentManager.beginTransaction().replace(mainContainer.id, fragment).commit()
        return true

    }

    override fun onLoadMatches() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadPeople() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
