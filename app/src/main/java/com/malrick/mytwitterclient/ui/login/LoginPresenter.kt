package com.malrick.mytwitterclient.ui.login

import com.malrick.mytwitterclient.ui.base.BasePresenter

class LoginPresenter : BasePresenter<LoginMvpView>() {

    companion object {
        // Code retourné par OauthActivity en cas de succès du login
        val CODE_OAUTH = 0
    }

    fun performLogin() {
        // Volonté initiale de rediriger l'utilisateur vers l'activité d'authentification
        // mvpView?.showOauthActivity(CODE_OAUTH)

        // Abandon de login avec les clés
        // On se contentera d'utiliser le token renseigné dans l'instance twitter
        onResultOk()
    }

    fun onResultOk() {
        // Redirection de l'utilisateur vers la suite (succès du login)
        mvpView?.moveOn()
    }

    fun onResultCanceled() {
        // Message d'erreur (échec du login)
        mvpView?.showLoginCanceled()
    }

}