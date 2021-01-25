package com.example.xylophone

import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi //soundPool버전 맞추기1

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)//soundPool버전 맞추기2
    private val soundPool = SoundPool.Builder().setMaxStreams(10).build()
    private val sounds = listOf(
        Pair(R.id.do1, R.raw.do1), //textView(first)와 음원(second)을 짝지어주기=>pair은 객체
        Pair(R.id.re, R.raw.re),
        Pair(R.id.mi, R.raw.mi),
        Pair(R.id.fa, R.raw.fa),
        Pair(R.id.sol, R.raw.sol),
        Pair(R.id.la, R.raw.la),
        Pair(R.id.si, R.raw.si),
        Pair(R.id.do2, R.raw.do2),
    )
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)//soundPool 버전맞추기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //forEach를 사용하여 메서드를 {}안에 전해줌=>sounds list 순서대로 들어감.
        sounds.forEach{turn(it)} //sounds는 pair로 만들어진 list=>it이라는 파라메터

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP) //soundPool 버전맞추기
    private fun turn(it: Pair<Int, Int>) { //이때의 it은 Pair
        val soundId = soundPool.load(this, it.second, 1)
        findViewById<TextView>(it.first).setOnClickListener {
            soundPool.play(soundId,1.0F,1.0F,0,0,1.0F)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP) //soundPool 버전맞추기
    override fun onDestroy(){
        super.onDestroy()
        soundPool.release()
    }
}