package com.example.cristinabautista.musica

import android.app.Service
import android.content.Intent
import android.os.IBinder
import sun.audio.AudioPlayer.player



class Service : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getPosn(): Int {
        return player.getCurrentPosition()
    }

    fun getDur(): Int {
        return player.getDuration()
    }

    fun isPng(): Boolean {
        return player.isPlaying()
    }

    fun pausePlayer() {
        player.pause()
    }

    fun seek(posn: Int) {
        player.seekTo(posn)
    }

    fun go() {
        player.start()
    }

    fun playPrev(): void{
        songPosn--
        if(songPosn&lt;0) songPosn=songs.size()-1
        playSong()
    }
}