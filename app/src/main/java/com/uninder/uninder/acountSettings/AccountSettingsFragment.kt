package com.uninder.uninder.acountSettings

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceScreen
import com.uninder.uninder.R
import org.jetbrains.anko.toast


private const val EDIT_NAME = "preference_name"
private const val EDIT_DESC = "preference_desc"
private const val CLOSE_SESSION = "preference_close_session"
private const val CHANGE_PHOTO = "preference_change_photo"

private const val REQUEST_IMAGE_GET = 1

class AccountSettingsFragment : PreferenceFragmentCompat(), AccountSettingsView {


    private lateinit var presenter: AccountSettingsPresenter

    private lateinit var listener: OnCloseSessionListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCloseSessionListener) {
            listener = context
            presenter = AccountSettingsPresenterImpl(this, context)
        } else
            throw RuntimeException("${context.toString()} debe implementar OnCloseSessionListener")
    }


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        addChangeEventListener(EDIT_DESC)
        addChangeEventListener(EDIT_NAME)

    }


    private fun addChangeEventListener(key: String) {
        findPreference(key).onPreferenceChangeListener = Preference.OnPreferenceChangeListener(
                { preference, newValue ->
                    onPreferenceChanged(preference, newValue)
                }
        )
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        when (preference?.key) {

            CLOSE_SESSION -> this.presenter.closeSession(listener)
            CHANGE_PHOTO -> this.presenter.changePicture()
        }

        return super.onPreferenceTreeClick(preference)
    }


    private fun onPreferenceChanged(preference: Preference?, newValue: Any?): Boolean {

        when (preference?.key) {

            EDIT_NAME -> this.presenter.editNameDialog(newValue)
            EDIT_DESC -> this.presenter.editDescDialog(newValue)
        }

        return true
    }


    override fun startPictureIntent() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, REQUEST_IMAGE_GET)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {

            val contentURI = data?.data
            if (contentURI != null) {
                this.presenter.savePicture(contentURI)
            }
        }

    }

    override fun showSuccessOnUploadPic() {
        context!!.toast(R.string.photUploadSuccess)
    }

    override fun showErrorOnUploadPic() {
        context!!.toast(R.string.photUploadError)
    }

    override fun showClosedSessionToast() {
        context!!.toast(R.string.loggedOut)
    }

    interface OnCloseSessionListener {
        fun closeSession()
    }


}