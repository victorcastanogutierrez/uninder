package com.uninder.uninder

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.acountSettings.AccountSettingsFragment
import com.uninder.uninder.findPeople.FindPeopleFragment
import com.uninder.uninder.getMatches.MatchesFragment
import com.uninder.uninder.mainScreen.MainScreenPresenter
import com.uninder.uninder.mainScreen.MainScreenPresenterImpl
import com.uninder.uninder.mainScreen.MainScreenView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class MainActivity : AppCompatActivity(), MainScreenView, AccountSettingsFragment.OnCloseSessionListener {

    private lateinit var presenter: MainScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        this.presenter = MainScreenPresenterImpl(this, this)
        this.presenter.checkSetUp()
    }

    override fun askForPreferences() {
        var genders = resources.getStringArray(R.array.gender)
        alert {
            title = "Configuración"
            var selectedGender = 1
            var searchGender = 2
            customView {
                verticalLayout {
                    textView("Género")
                    radioGroup {
                        orientation= LinearLayout.HORIZONTAL
                        var n=1
                        genders.forEach {
                            radioButton {
                                id=n
                                text = it.toString()
                                if (id==selectedGender){
                                    isChecked = true
                                }
                                onClick {
                                    selectedGender=id
                                }
                            }.lparams{leftPadding=dip(20)}
                            n++
                        }
                    }
                    textView("Género buscado")
                    radioGroup {
                        orientation= LinearLayout.HORIZONTAL
                        var n=1
                        genders.forEach {
                            radioButton {
                                id=n
                                text = it.toString()
                                if (id==searchGender){
                                    isChecked = true
                                }
                                onClick {
                                    searchGender=id
                                }
                            }.lparams{leftPadding=dip(20)}
                            n++
                        }
                    }
                    var description = editText(getString(R.string.tellus))
                    padding = dip(16)
                    positiveButton("Aceptar") {
                        presenter.saveConfig(selectedGender, searchGender, description.text.toString())
                    }
                }
            }
        }.show()
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
                R.id.menu_account -> changeFragment(AccountSettingsFragment(),it)
                else -> changeFragment(FindPeopleFragment(), it)
            }
        }
    }
    
    override fun closeSession() {
        FirebaseAuth.getInstance().signOut()
        supportFragmentManager.popBackStack()
        finish()
    }

    private fun changeFragment(fragment: Fragment, menuItem: MenuItem): Boolean {
        menuItem.isEnabled = true
        supportFragmentManager.beginTransaction().replace(mainContainer.id, fragment).commit()
        return true

    }



}
