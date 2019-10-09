package com.malrick.mytwitterclient.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.malrick.mytwitterclient.R
import com.malrick.mytwitterclient.data.twitter.TwitterAPI
import com.malrick.mytwitterclient.ui.base.MvpView
import com.malrick.mytwitterclient.ui.timeline.TimelineFragment
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import twitter4j.Paging
import twitter4j.Status
import twitter4j.Twitter
import java.lang.Exception

class MainActivity : AppCompatActivity(), MvpView {

    var twitter: Twitter = com.malrick.mytwitterclient.data.twitter.Twitter.getInstance()

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_main)

        // On remplace l'objet "container_framelayout" de notre layout par une nouvelle instance de fragment de timeline
        supportFragmentManager.beginTransaction().replace(R.id.container_frameLayout, TimelineFragment.newInstance()).commit()
    }

}
