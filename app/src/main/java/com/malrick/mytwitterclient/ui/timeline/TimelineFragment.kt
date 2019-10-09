package com.malrick.mytwitterclient.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.malrick.mytwitterclient.R
import com.malrick.mytwitterclient.data.model.Tweet

class TimelineFragment : Fragment(), TimelineMvpView, InteractionListener {

    companion object {
        fun newInstance() = TimelineFragment()
    }

    // Fournit des données à l'adapter à la demande
    private val presenter: TimelinePresenter = TimelinePresenter()

    // Gère la création d'une vue pour chaque objet du dataset
    private lateinit var adapter: TimelineAdapter

    // RecyclerView : Permet d'afficher une longue liste d'objets, en tirant parti du faible nombre d'éléments affichés simultanément à l'écran
    // Les vues "hors de l'écran" sont recyclées, cela permet une fluidité de l'affichage
    // La création d'une vue se fait avec la méthode "createViewHolder" de l'adapter
    // Le recyclage d'une vue se fait avec la méthode "onBindViewHolder" de l'adapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        presenter.attachView(this)
        adapter = TimelineAdapter(this)
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

            // Inflate du xml => obtention d'une view
            val rootView = inflater.inflate(R.layout.fragment_timeline, container, false)

            // Initialisation du recyclerview depuis la ressource
            recyclerView = rootView?.findViewById(R.id.tweetsRecyclerView) as RecyclerView

            // Configuration du recyclerview
            val linearLayoutManager = LinearLayoutManager(activity)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

            // Appel de "getTweets" sur le presenter, il appellera ensuite la méthode "showTweet" qui remplira la liste de l'adapter
            if (adapter.tweets.isEmpty())
                presenter.getTweets()

            // Retour de la view générée
            return rootView
        }

    // Méthode appelée par le presenter afin d'afficher les tweets qu'il a récupérés via l'API
    override fun showTweets(tweets: MutableList<Tweet>) {
        adapter.tweets = tweets
        adapter.notifyDataSetChanged()
    }

    override fun showTweet(tweet: Tweet) {
        // Future fonction qui permettra l'affichage d'un unique tweet
    }

    override fun showMoreTweets(tweets: MutableList<Tweet>) {
        // Future fonction permettant de charger plus de tweet dans la liste
    }

}