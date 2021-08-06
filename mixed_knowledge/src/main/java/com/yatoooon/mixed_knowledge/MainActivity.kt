package com.yatoooon.mixed_knowledge

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private var pause = false

    //入门模式 ，避免传入参数匹配不上的错误
    companion object {
        private const val EXTRAS_1 = "EXTRAS_1"
        private const val EXTRAS_2 = "EXTRAS_2"

        fun getStartIntent(context: Context, extras1: String, extras2: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRAS_1, extras1)
            intent.putExtra(EXTRAS_2, extras2)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //MediaPlayer 播放音频文件
//        playWithMediaPlayer()
        //键盘显示与隐藏
//        softKeyBard()
        //截屏
//        bt_screenshot.setOnClickListener {
//            val screenShot = getScreenShot(window.decorView.rootView)
//            image.setImageBitmap(screenShot)
//        }
        //pdf的显示
//        showPDF()


    }

//    private fun showPDF() {
//        webView.webViewClient = WebViewClient()
//        webView.settings.setSupportZoom(true)
//        webView.settings.javaScriptEnabled = true
//        val url =
//            "https://mindorks.s3.ap-south-1.amazonaws.com/courses/MindOrks_Android_Online_Professional_Course-Syllabus.pdf"
//        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")
//    }


    private fun getScreenShot(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

    private fun softKeyBard() {
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editext)
        editText.requestFocus()
        val constraintLayout: ConstraintLayout = findViewById(R.id.rootview)
        constraintLayout.viewTreeObserver.addOnGlobalLayoutListener {
            val rec = Rect()
            constraintLayout.getWindowVisibleDisplayFrame(rec)
            //finding screen height
            val screenHeight = constraintLayout.rootView.height
            //finding keyboard height
            val keypadHeight = screenHeight - rec.bottom
            if (keypadHeight > screenHeight * 0.15) {
                Toast.makeText(this@MainActivity, "VISIBLE KEYBOARD", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, "NO KEYBOARD", Toast.LENGTH_LONG).show()
            }
        }
        button.setOnClickListener {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }


    fun playWithMediaPlayer() {
//        //(1)播放raw文件夹下
//        var mediaPlayer1: MediaPlayer = MediaPlayer.create(this, R.raw.simple)
//        mediaPlayer1.start()
//
//        //(2)播放uri 需要动态申请权限，我为了简单把它变为了系统应用
//        val sampleUri: Uri =
//            Uri.fromFile(File(Environment.getExternalStorageDirectory().path + "/Music/simple.mp3")) // your uri here
//        val mediaPlayer2: MediaPlayer = MediaPlayer().apply {
//            setAudioStreamType(AudioManager.STREAM_MUSIC)
//            setDataSource(
//                applicationContext,
//                sampleUri
//            ) //to set media source and send the object to the initialized state
//            prepare() //to send the object to prepared state
//            start() //to start the music and send the object to the started state
//        }
//
//        //(3)播放url
//        val sampleUrl =
//            "http://yinyueshiting.baidu.com/data2/music/134380372/30299672000128.mp3" // your URL here
//        val mediaPlayer3: MediaPlayer = MediaPlayer().apply {
//            setAudioStreamType(AudioManager.STREAM_MUSIC) //to send the object to the initialized state
//            setDataSource(sampleUrl) //to set media source and send the object to the initialized state
//            prepare() //to send the object to the prepared state, this may take time for fetching and decoding
//            start() //to start the music and send the object to started state
//        }

        //播放状态的改变
        bt_play.setOnClickListener {
            if (pause) { //initially, pause is set to false
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
                //playing audio when in paused state
            } else {
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.simple)
                mediaPlayer.start()
                //playing audio when in prepared state
            }
        }
        bt_pause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                pause = true
                //audio is paused here
            }
        }
        bt_stop.setOnClickListener {
            if (mediaPlayer.isPlaying || pause) {
                pause = false
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                //audio is stopped here
            }
        }
    }
}
