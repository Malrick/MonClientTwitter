package com.malrick.mytwitterclient.data.twitter

import io.reactivex.Single
import twitter4j.Paging
import twitter4j.Status

// Singleton (object) d'utilisation de l'API Twitter
object TwitterAPI {

    private val twitter = Twitter.getInstance()

    // Fonction de timeline : renvoie une collection générique (MutableList) de status
    // Cette collection est observable : Utilisation de single (reactiveX)
    fun getHomeTimeline(paging: Paging): Single<MutableList<Status>> =
        Single.fromCallable { twitter.getHomeTimeline(paging) }

}