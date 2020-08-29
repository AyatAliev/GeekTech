package com.geektech.geektech.ui.detailLesson

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.SparseArray
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.geektech.geektech.R
import com.geektech.geektech.ui.utils.PlayerManager
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.lawlett.youtubeparcer.utils.*


class DetailVideoActivity : AppCompatActivity(), CallBacks {
//    var list = mutableListOf<PlaylistItem>()
    private lateinit var player: Player
    private lateinit var playerManager: PlayerManager

    private var listOfFormatVideo = mutableListOf<YoutubeVideo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setupToSubscribe()
        setupExoPlayer()
    }

    companion object {
        var myVideoId: String? = "JbA0FWst"
        fun instanceVideoDetail(activity: Activity?, id: String?) {
            val intent = Intent(activity, DetailVideoActivity::class.java)
            activity?.startActivity(intent)
            this.myVideoId = id
        }
    }


    private fun setupToSubscribe() {
                getActualUrl("DOsqGqqSAYY")

        }


    @SuppressLint("StaticFieldLeak")
    private fun getActualUrl(url: String?) {
        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                var i = 0
                var itag: Int
                if (ytFiles != null) {
                    while (i < ytFiles.size()) {
                        itag = ytFiles.keyAt(i)
                        var ytFile = ytFiles.get(itag)
                        if (ytFile.format.height == -1 || ytFile.format.height >= 360) {
                            addFormatToList(ytFile, ytFiles)
                        }
                        i++
                    }
                }
                val videoUrl: YoutubeVideo? = listOfFormatVideo.last()
                playVideo(videoUrl?.videoFile?.url)
            }

        }.extract(url, true, true)
    }

    private fun setupExoPlayer() {
        playerManager = PlayerManager.getSharedInstance(this)
        player = playerManager.playerView.player
    }

    private fun addFormatToList(ytFile: YtFile, ytFiles: SparseArray<YtFile>) {
        var height = ytFile.format.height
        if (height != -1) {
            for (video in listOfFormatVideo) {
                if (video?.height == height && (video?.videoFile == null || video.videoFile!!.format.fps ==
                            ytFile.format.fps)
                ) {
                    return
                }
            }
            val yVideo = YoutubeVideo()
            yVideo.height = height
            if (ytFile.format.isDashContainer) {
                if (height > 0) {
                    yVideo.videoFile = ytFile
                    yVideo.audioFile = ytFiles.get(140)
                } else {
                    yVideo.audioFile = ytFile
                }
            }
            listOfFormatVideo.add(yVideo)
            Log.e("quality", "addFormatToList: $listOfFormatVideo")
        }
    }

    private fun playVideo(url: String?) {
        val play_view = findViewById<SimpleExoPlayerView>(R.id.player_view)
        play_view?.player = player
        PlayerManager.getSharedInstance(this).playStream(url)
        PlayerManager.getSharedInstance(this).setPlayerListener(this)

    }

    override fun onItemClickOnItem(albumId: Int) {
    }

    override fun onPlayingEnd() {
    }
}
