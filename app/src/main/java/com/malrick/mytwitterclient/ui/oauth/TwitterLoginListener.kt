package com.malrick.mytwitterclient.ui.oauth

import twitter4j.auth.AccessToken

interface TwitterLoginListener {

    fun onSuccess(accessToken: AccessToken)

    fun onFailure(resultCode: Int)
}
