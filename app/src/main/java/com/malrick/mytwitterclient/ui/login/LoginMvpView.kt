package com.malrick.mytwitterclient.ui.login

import com.malrick.mytwitterclient.ui.base.MvpView

interface LoginMvpView : MvpView {

    fun showOauthActivity(requestCode: Int)

    fun showLoginError()

    fun showLoginCanceled()

    fun moveOn()
}