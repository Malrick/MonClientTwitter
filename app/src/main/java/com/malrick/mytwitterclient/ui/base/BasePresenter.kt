package com.malrick.mytwitterclient.ui.base

open class BasePresenter<V : MvpView> : Presenter<V> {

    protected var mvpView: V? = null

    override fun attachView(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        this.mvpView = null
    }

}