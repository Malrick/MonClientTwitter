package com.malrick.mytwitterclient.ui.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.malrick.mytwitterclient.R
import com.malrick.mytwitterclient.data.model.Tweet
import com.malrick.mytwitterclient.ui.timeline.holders.BaseViewHolder
import com.malrick.mytwitterclient.ui.timeline.holders.StatusViewHolder
import java.util.ArrayList

class TimelineAdapter(val listener: InteractionListener) : RecyclerView.Adapter<BaseViewHolder>(){

    // ArrayList de tweets
    var tweets: MutableList<Tweet> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        // On ne gère pour le moment que des tweets basiques :
        // Le Holder retourné est obligatoirement un StatusViewHolder pour le moment.
        return StatusViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.tweet_basic, parent, false), listener)
    }

    // Override de la méthode de recyclerview, retourne le nombre d'objets de l'adapter
    override fun getItemCount() = tweets.size

    // Bind d'un view holder, en lui fournissant le contenu approprié de l'arrayList de tweet
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder?.setup(tweets[position])
    }

}