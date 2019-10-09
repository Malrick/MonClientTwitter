package com.malrick.mytwitterclient.ui.oauth

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.util.AttributeSet
import android.webkit.*
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken

// Ne pas hésiter à aller voir le projet open source dont cette view est inspirée :
// http://www.java2s.com/Open-Source/Android_Free_Code/Twitter/twitter4j/TakahikoKawasaki_TwitterOAuthView.htm

class OAuthView  : WebView {

    constructor(context : Context?) : super(context)
    {
        init()
    }

    constructor(context : Context?, attrs: AttributeSet?) : super(context, attrs)
    {
        init()
    }

    constructor(context : Context?, attrs: AttributeSet?, defStyleAttr : Int) : super(context, attrs, defStyleAttr)
    {
        init()
    }

    // Liste de code de retour, selon le déroulement du process
    val SUCCESS = 0
    val CANCELLATION = 1
    val REQUEST_TOKEN_ERROR = 2
    val ACCESS_TOKEN_ERROR = 3

    // AsyncTask
    private var twitterOAuthTask: TwitterOAuthTask? = null

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        val settings = settings
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.javaScriptEnabled = true
        settings.builtInZoomControls = true
    }

    // Lancement de l'asynctask de login
    fun start(consumerKey: String, consumerSecret: String, callbackUrl: String, listener: TwitterLoginListener) {
        synchronized(this) {
            twitterOAuthTask = TwitterOAuthTask()
            twitterOAuthTask?.setCreator(this)
            twitterOAuthTask!!.execute(consumerKey, consumerSecret, callbackUrl, listener)
        }
    }

    private inner class TwitterOAuthTask : AsyncTask<Any, Void, Int>() {

        // URL de callback appelée par l'API Twitter après un login success
        private var callbackUrl: String? = "https://api.twitter.com/oauth/request_token"

        // Récupération de l'instance twitter
        private var twitter: Twitter = com.malrick.mytwitterclient.data.twitter.Twitter.getInstance()

        // Les tokens
        private var requestToken: RequestToken? = null
        private var accessToken: AccessToken? = null

        // Référence à l'outer class
        private var oAuthView : OAuthView? = null

        // Booléen d'acquisition de l'autorisation
        // Volatile : immédiatement visible pour les autres threads
        @Volatile
        private var authorizationDone: Boolean = false

        // Setter de référence à l'outer class
        fun setCreator(oAuthView : OAuthView)
        {
            this.oAuthView = oAuthView
        }

        // Avant d'éxécuter la tâche asynchrone, on dit que le client de cette webview (outerclass)
        // est le client web local (innerclass)
        override fun onPreExecute() {
            oAuthView?.webViewClient = LocalWebViewClient()
        }

        // La tâche asynchrone en elle-même
        override fun doInBackground(vararg args: Any): Int? {
            if (isCancelled)
                return CANCELLATION

            // Récupération du requestToken
            try {
                requestToken = twitter?.getOAuthRequestToken()
            } catch (e: TwitterException) {
                e.printStackTrace()
                return REQUEST_TOKEN_ERROR
            }

            // Récupération de l'autorisation (attente du passage de "authorization done" à true)
            val cancelled = waitForAuthorization()
            if (cancelled || isCancelled)
                return CANCELLATION

            // Récupération de l'access token
            try {
                accessToken = twitter!!.getOAuthAccessToken(requestToken)
            } catch (e: TwitterException) {
                e.printStackTrace()
                return ACCESS_TOKEN_ERROR
            }

            // Pas d'erreur avant : succès
            return SUCCESS
        }

        override fun onPostExecute(result: Int?) {
            var result = result
            if (result == null)
                result = CANCELLATION

            if (result == SUCCESS) {
                onAuthSuccess()
            } else {
                onAuthFailure(result)
            }

        }

        override fun onCancelled() {
            super.onCancelled()

            onAuthFailure(CANCELLATION)

        }

        private fun onAuthSuccess() {
        }

        private fun onAuthFailure(result: Int?) {
        }

        // On attend tant que l'autorisation n'a pas été accordée
        private fun waitForAuthorization(): Boolean {
            while (!authorizationDone) {
                if (isCancelled)
                    return true

                synchronized(this) {
                    try {
                        (this as java.lang.Object).wait()
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }

            return false
        }

        // Méthode faisant passer authorizationDone à true
        private fun notifyAuthorization() {
            authorizationDone = true

            synchronized(this) {
                (this as java.lang.Object).notify()
            }
        }

        private inner class LocalWebViewClient : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // Si on retourne vers autre chose que l'URL de callback, alors pas d'autorisation
                if (!url.startsWith(callbackUrl!!))
                    return false

                // Sinon, c'est que l'on a appelé l'URL de ballback, notification d'autorisation
                notifyAuthorization()
                return true
            }
        }
    }
}
