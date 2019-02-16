package com.example.cristinabautista.musica

class Song(thisId: Long, thisTitle: String, thisArtist: String) {
    private val id: Long = 0
    private val title: String? = null
    private val artists: String? = null

    fun getID(): Long {
        return id
    }

    fun getTitle(): String? {
        return title
    }

    fun getArtist(): String? {
        return artists
    }


}