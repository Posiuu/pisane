package com.pisane.pisane.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.pisane.pisane.R

class MusicService : Service() {
    private lateinit var player: MediaPlayer
    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.music)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player.start()
        return START_STICKY
    }

    override fun onStart(intent: Intent, startId: Int) {
        player.start()
    }

    override fun onDestroy() {
        player.stop()
        player.release()
    }

    override fun onLowMemory() {

    }
}