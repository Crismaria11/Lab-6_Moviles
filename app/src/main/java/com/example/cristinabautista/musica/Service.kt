package com.example.cristinabautista.musica

import android.app.Service
import android.content.Intent
import android.os.IBinder
import sun.audio.AudioPlayer.player
import java.util.Random;
import android.app.Notification;
import android.app.PendingIntent;



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
        Intent notIntent = new Intent(this, MainActivity.class);
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendInt = PendingIntent.getActivity(this, 0,
        notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentIntent(pendInt)
            .setSmallIcon(R.drawable.play)
            .setTicker(songTitle)
            .setOngoing(true)
            .setContentTitle(&quot;Playing&quot;)
        .setContentText(songTitle);
        Notification not = builder.build();

        startForeground(NOTIFY_ID, not);
    }

    fun playPrev(): void{
        songPosn--
        if(songPosn&lt;0) songPosn=songs.size()-1
        playSong()
    }

    //skip to next
    fun playNext(): void{
        songPosn++;
        if(songPosn&gt;=songs.size()) songPosn=0;
        playSong();
    }

    fun getDuration(): int {
        if(musicSrv!=null &amp;&amp; musicBound &amp;&amp; musicSrv.isPng())
        return musicSrv.getDur();
        else return 0;
    }

    fun isPlaying(): boolean {
        if(musicSrv!=null &amp;&amp; musicBound)
        return musicSrv.isPng();
        return false;
    }

    fun pause() {
        musicSrv.pausePlayer()
    }

    fun seekTo(pos: Int) {
        musicSrv.seek(pos)
    }

    fun start() {
        musicSrv.go()
    }


}