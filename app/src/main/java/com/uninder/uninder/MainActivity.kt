package com.uninder.uninder

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.uninder.uninder.acountSettings.AccountSettingsFragment
import com.uninder.uninder.findPeople.FindPeopleFragment
import com.uninder.uninder.getMatches.MatchesFragment
import com.uninder.uninder.handler.NotificationHelper
import com.uninder.uninder.mainScreen.MainScreenPresenter
import com.uninder.uninder.mainScreen.MainScreenPresenterImpl
import com.uninder.uninder.mainScreen.MainScreenView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class MainActivity : AppCompatActivity(), MainScreenView, AccountSettingsFragment.OnCloseSessionListener {

    private lateinit var presenter: MainScreenPresenter

    private lateinit var indeterminateDialog: Dialog

    private lateinit var mNotificationHelper: NotificationHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNotificationHelper = NotificationHelper(this)
        this.requestedOrientation =ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()
        this.presenter = MainScreenPresenterImpl(this, this)
        this.presenter.checkSetUp()
    }

    override fun askForPreferences() {
        var genders = resources.getStringArray(R.array.gender)
        alert {
            title = getString(R.string.initialConfig)
            var selectedGender = 1
            var searchGender = 2
            isCancelable = false
            customView {
                verticalLayout {
                    textView(getString(R.string.gender))
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
                    textView(getString(R.string.genderLooked))
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
                    var description = editText{
                        hint = getString(R.string.tellus)
                    }
                    padding = dip(16)
                    positiveButton("Aceptar") {
                        presenter.saveConfig(selectedGender, searchGender, description.text.toString())
                    }
                }
            }
        }.show()
    }

    override fun showWelcomeMessage() {
        alert(getString(R.string.welcomeMessageBody)) {
            title = getString(R.string.welcomeMessageTitle)
            yesButton {
                initialize()
            }
        }.show()
    }

    override fun initialize() {
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

    override fun showIndeterminateLoading() {
        this.indeterminateDialog = indeterminateProgressDialog(getString(R.string.savingConfiguration))
        this.indeterminateDialog.show()
    }

    override fun hideIndeterminateLoading() {
        indeterminateDialog.dismiss()
    }

}
