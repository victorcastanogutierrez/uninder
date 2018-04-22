package com.uninder.uninder.acountSettings

interface AccountSettingsView {


    /***
     * Initialises the change picture intent
     */
    fun startPictureIntent()

    /***
     * Initialises the notifications intent
     */
    fun startNotificationsIntent()

    /***
     * Retrieves the image data from the intent
     */
    fun showSuccessOnUploadPic()

    /***
     *Shows error if the images wasn´t uploaded correctily
     */
    fun showErrorOnUploadPic()

    /***
     * Shows message after closing session
     */
    fun showClosedSessionToast()
}