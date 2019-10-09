package com.malrick.mytwitterclient.ui.timeline.holders

import android.view.View
import com.malrick.mytwitterclient.common.utils.Utils
import com.malrick.mytwitterclient.data.model.Tweet
import com.malrick.mytwitterclient.ui.timeline.InteractionListener

open class StatusViewHolder(container: View, listener: InteractionListener) :
    BaseViewHolder(container, listener) {

    // Remplissage du layout d'un tweet standard
    override fun setup(tweet: Tweet) {

        // Texte du tweet : son contenu sans ses médias
        statusTextView.text = tweet.getTextWithoutMediaURLs()

        // Le nom de l'utilisateur
        userNameTextView.text = tweet.user.name

        // "@" de l'utilisateur
        userScreenNameTextView.text = "@${tweet.user.screenName}"

        // Temps depuis lequel le tweet a été posté : utilisation de l'utilitaire de date
        timeTextView.text = " • ${Utils.formatDate(tweet.timeStamp)}"
    }

}