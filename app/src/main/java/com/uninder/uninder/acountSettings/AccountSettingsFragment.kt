package com.uninder.uninder.acountSettings

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.uninder.uninder.R
import org.jetbrains.anko.toast


private const val EDIT_NAME = "preference_name"
private const val EDIT_DESC = "preference_desc"
private const val CLOSE_SESSION = "preference_close_session"
private const val CHANGE_PHOTO = "preference_change_photo"

private const val REQUEST_IMAGE_GET = 1

class AccountSettingsFragment : PreferenceFragmentCompat(), AccountSettingsView {

    private val presenter = AccountSettingsPresenterImpl(this)

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }


    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        when (preference?.key) {

            EDIT_NAME -> this.presenter.editNameDialog()
            EDIT_DESC -> this.presenter.editDescDialog()
            CLOSE_SESSION -> this.presenter.closeSession()
            CHANGE_PHOTO -> this.presenter.changePicture()
            else -> {
            }
        }

        return super.onPreferenceTreeClick(preference)
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


}