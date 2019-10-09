package com.malrick.mytwitterclient.data.twitter

import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

// Singleton (object) twitter
object Twitter {

    private var factory: TwitterFactory? = null

    // Clés utilisateur (à remplacer)
    var TWITTER_CONSUMER_KEY = "Consumer key"
    var TWITTER_CONSUMER_SECRET = "Consumer secret"
    var TWITTER_ACCESS_TOKEN = "Access token"
    var TWITTER_ACCESS_TOKEN_SECRET = "Access token secret"

    // Méthode privée de récupération de la TwitterFactory
    private fun getFactory(): TwitterFactory {

        // Initialisation de la factory si premier appel
        if(factory == null)
        {
            factory = TwitterFactory(ConfigurationBuilder()
                .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET)
                .build())
        }

        return factory as TwitterFactory
    }

    // Méthode d'appel externe
    fun getInstance(): Twitter = getFactory().getInstance()

}