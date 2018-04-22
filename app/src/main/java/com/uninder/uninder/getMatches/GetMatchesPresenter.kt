package com.uninder.uninder.getMatches

interface GetMatchesPresenter {


    /**
     * Loads matches data
     */
    fun loadMatches()

    /**
     * Loads the matches images
     */
    fun loadMatchesImages(dataList:MutableList<String>,
                          onFinish: (data: MutableMap<String, String>) -> Unit)
}