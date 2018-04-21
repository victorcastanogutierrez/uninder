package com.uninder.uninder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.Toast
import com.uninder.uninder.mainScreen.MainScreenPresenter
import com.uninder.uninder.mainScreen.MainScreenPresenterImpl
import com.uninder.uninder.mainScreen.MainScreenView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class MainActivity : AppCompatActivity(), MainScreenView {

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
                        presenter.saveConfig(selectedGender, searchGender, description)
                    }
                }
            }
        }.show()
    }
}
