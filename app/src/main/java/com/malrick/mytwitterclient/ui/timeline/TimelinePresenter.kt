package com.malrick.mytwitterclient.ui.timeline

import com.malrick.mytwitterclient.data.model.Tweet
import com.malrick.mytwitterclient.data.twitter.TwitterAPI
import com.malrick.mytwitterclient.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import twitter4j.Paging

open class TimelinePresenter : BasePresenter<TimelineMvpView>() {

    // On récupère les tweets 50 par 50, nombre de "pages" de 50 actuellement en mémoire
    var page: Int = 1

    // Ensemble d'objets disposable
    protected val disposables = CompositeDisposable()

    open fun getTweets() {
        disposables.add(TwitterAPI.getHomeTimeline(Paging(page, 50))
            // L'observation se fait sur le thread principal
            .observeOn(AndroidSchedulers.mainThread())
            // L'observable agira sur le scheduler input / output (appel réseau, peu consommateur pour le CPU)
            .subscribeOn(Schedulers.io())
            // Opération observée
            .subscribe({
                mvpView?.showTweets(it.map(::Tweet).toMutableList())
                page++ },{}))
    }


    override fun detachView() {
        super.detachView()
        disposables.clear()
    }

    open fun getMoreTweets() {
        // Future fonction gérant la récupération de plus de tweets
    }

    open fun onRefresh() {
        // Future fonction gérant le rafraîchissement de la timeline
    }

}