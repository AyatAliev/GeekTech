package com.geektech.geektech.ui.detailLesson

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.geektech.geektech.R
import com.geektech.geektech.ui.model.Lesson
import com.geektech.geektech.ui.utils.PlayerManager
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.lawlett.youtubeparcer.utils.CallBacks
import com.lawlett.youtubeparcer.utils.YoutubeVideo
import java.util.*


class DetailVideoActivity : AppCompatActivity(), CallBacks {
    private lateinit var player: Player
    private lateinit var playerManager: PlayerManager
    private  var exoPlayer: SimpleExoPlayer?=null

    private var listOfFormatVideo = mutableListOf<YoutubeVideo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setupToSubscribe()
        setupExoPlayer()

        FirebaseFirestore
                .getInstance()
                .collection("lessons")
                .get()
                .addOnCompleteListener { task: Task<QuerySnapshot?> ->
                    for (lesson in Objects.requireNonNull(task.result)?.toObjects(Lesson::class.java)!!) {
                        val title = findViewById<TextView>(R.id.title)
                        val desc = findViewById<TextView>(R.id.desc)
                        title.text=lesson.title
                        desc.text=lesson.desc

                    }
                }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        val play_view = findViewById<SimpleExoPlayerView>(R.id.player_view)

        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            //First Hide other objects (listview or recyclerview), better hide them using Gone.
            val params = play_view.getLayoutParams()
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            play_view.setLayoutParams(params)
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            //unhide your objects here.
            val params = play_view.getLayoutParams()
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = 600
            play_view.setLayoutParams(params)
        }
    }

    private fun setupToSubscribe() {
//                getActualUrl("DOsqGqqSAYY")
        initExoPlayer()
    }

    private fun initExoPlayer() {
        val play_view = findViewById<SimpleExoPlayerView>(R.id.player_view)
        exoPlayer = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(this),
                DefaultTrackSelector(),
                DefaultLoadControl())
        play_view?.player = exoPlayer
        var dataSourceFactory: com.google.android.exoplayer2.upstream.DataSource.Factory = DefaultDataSourceFactory(
                this, com.google.android.exoplayer2.util.Util.getUserAgent
        (this, "GeekTech"))
        var videoSource: MediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("https://www.learningcontainer.com/bfd_download/sample-mp4-file/"))
        exoPlayer?.prepare(videoSource,true,false)
        exoPlayer?.playWhenReady = true
    }




    /*@SuppressLint("StaticFieldLeak")
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
*/
    private fun setupExoPlayer() {
        playerManager = PlayerManager.getSharedInstance(this)
        player = playerManager.playerView.player
    }

   /* private fun addFormatToList(ytFile: YtFile, ytFiles: SparseArray<YtFile>) {
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

    }*/

    override fun onItemClickOnItem(albumId: Int) {
    }

    override fun onPlayingEnd() {
    }
}
