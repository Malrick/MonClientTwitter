package com.malrick.mytwitterclient.ui.base

interface Presenter<in V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()

}