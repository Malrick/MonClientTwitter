package com.malrick.mytwitterclient.ui.timeline.holders

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.malrick.mytwitterclient.data.model.Tweet
import com.malrick.mytwitterclient.ui.timeline.InteractionListener
import kotlinx.android.synthetic.main.item_userinfo.view.*
import kotlinx.android.synthetic.main.tweet_basic.view.*

abstract class BaseViewHolder(val container: View, val listener: InteractionListener) :
    RecyclerView.ViewHolder(container) {

    // Toutes les valeurs nécessaires à l'affichage de tweets
    protected val statusTextView: TextView = container.statusTextView
    protected val userNameTextView: TextView = container.userNameTextView
    protected val userScreenNameTextView: TextView = container.userScreenNameTextView
    protected val timeTextView: TextView = container.timeTextView

    // Fonction de mise en place du layout : Possibilité de faire des variations selon le type de tweet
    abstract fun setup(tweet: Tweet)

}
