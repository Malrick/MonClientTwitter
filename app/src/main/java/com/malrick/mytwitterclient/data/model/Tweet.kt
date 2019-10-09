package com.malrick.mytwitterclient.data.model

import twitter4j.MediaEntity
import twitter4j.Status
import twitter4j.User
import java.io.Serializable

class Tweet(val status: Status) : Serializable {

    // Valeurs utiles du tweet
    val text: String = status.text
    val timeStamp: Long = status.createdAt.time
    val user: User = status.user
    val mediaEntities: Array<MediaEntity> = status.mediaEntities

    // Récupération du texte sans les médias, pour permettre un formattage pertinent
    fun getTextWithoutMediaURLs(): String {
        var noUrlText = text
        for (i in mediaEntities.indices)
            noUrlText = text.replace(mediaEntities[i].url, "")
        return noUrlText
    }

}