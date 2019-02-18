package com.example.cristinabautista.musica

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import java.util.*
import android.widget.MediaController.MediaPlayerControl

class MainActivity : AppCompatActivity(), MediaPlayerControl {
    private String songTitle=&quot;&quot
    private static final int NOTIFY_ID=1

    override fun isPlaying(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun canSeekForward(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDuration(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBufferPercentage(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun seekTo(pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentPosition(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun canSeekBackward(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAudioSessionId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun canPause(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //instancias
    var songList: ArrayList<Song>? = null
    var songView: ListView? = null
    var controller: MusicController? = null
    private fun setController() {
        //set the controller up
        controller = MusicController(this)
        controller!!.setPrevNextListeners(object : View.OnClickListener {
            override fun onClick(v: View) {
                playNext()
            }
        }, object : View.OnClickListener {
            override fun onClick(v: View) {
                playPrev()
            }
        })
        controller!!.setMediaPlayer(this);
        controller!!.setAnchorView(findViewById(R.id.song_list));
        controller!!.setEnabled(true);
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songView = findViewById<ListView>(R.id.song_list)
        songList = ArrayList()
        getSongList()
        Collections.sort(songList, object : Comparator<Song> {
            override fun compare(a: Song, b: Song): Int {
                return a.getTitle()!!.compareTo(b.getTitle()!!)
            }
        })
        val songAdt = SongAdapter(this, songList)
        songView.setAdapter(songAdt)

        setController()

    }


    fun getSongList() {
        val musicResolver = contentResolver
        val musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val musicCursor = musicResolver.query(musicUri, null, null, null, null)
        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns
            val titleColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE)
            val idColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID)
            val artistColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST)
            //add songs to list
            do {
                val thisId = musicCursor.getLong(idColumn)
                val thisTitle = musicCursor.getString(titleColumn)
                val thisArtist = musicCursor.getString(artistColumn)
                songList!!.add(Song(thisId, thisTitle, thisArtist))
            } while (musicCursor.moveToNext())
        }
    }

    //play next
    private fun playNext() {
        musicSrv.playNext()
        controller!!.show(0)
    }

    //play previous
    private fun playPrev() {
        musicSrv.playPrev()
        controller!!.show(0)
    }

    override fun canPause(): Boolean {
        return true
    }

    override fun canSeekBackward(): Boolean {
        return true
    }

    override fun canSeekForward(): Boolean {
        return true
    }

    @Override
    override fun getCurrentPosition(): int {
        if(musicSrv!=null &amp;&amp; musicBound &amp;&amp; musicSrv.isPng())
        return musicSrv.getPosn();
        else return 0;
    }


}
