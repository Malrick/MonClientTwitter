package com.malrick.mytwitterclient.ui.timeline

import com.malrick.mytwitterclient.data.model.Tweet
import com.malrick.mytwitterclient.ui.base.MvpView
import twitter4j.User

interface TimelineMvpView : MvpView {

    fun showTweets(tweets: MutableList<Tweet>)

    fun showTweet(tweet: Tweet)

    fun showMoreTweets(tweets: MutableList<Tweet>)

}