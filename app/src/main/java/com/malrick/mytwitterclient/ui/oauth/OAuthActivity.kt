package com.malrick.mytwitterclient.ui.oauth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import twitter4j.auth.AccessToken



class OAuthActivity : Activity(), TwitterLoginListener {

    private val view: OAuthView by lazy {OAuthView(this)}

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)
        this.view?.start("Consumer key", "Consumer secret", "Callback URL", this)
    }

    override fun onSuccess(accessToken: AccessToken) {
        setResult(RESULT_OK, Intent())
        finish()
    }

    override fun onFailure(resultCode: Int) {

    }

}
