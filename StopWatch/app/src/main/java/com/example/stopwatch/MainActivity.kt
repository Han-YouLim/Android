package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time=0 //시간
    private var isRunning = false //play=true, pause=false
    private var timerTask: Timer?= null

    lateinit var fab:FloatingActionButton //playbutton
    lateinit var resetFab:FloatingActionButton //resetbutton
    lateinit var secTextView:TextView
    lateinit var  milliTextView: TextView
    lateinit var lapButton: Button //랩타임 버튼
    lateinit var lapLayout:LinearLayout //랩타임 기록 레이아웃
    private var lap =1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //위젯 연결
        fab=findViewById<FloatingActionButton>(R.id.fab)
        resetFab=findViewById<FloatingActionButton>(R.id.resetFab)
        secTextView=findViewById<TextView>(R.id.secTextView)
        milliTextView=findViewById<TextView>(R.id.milliTextView)
        lapButton=findViewById<Button>(R.id.lapButton)
        lapLayout=findViewById<LinearLayout>(R.id.lapLayout)
        //playbutton이 눌렸을 때
        fab.setOnClickListener{
            isRunning = !isRunning

            if(isRunning){
                start()
            }else{
                pause()
            }
        }
        //랩버튼이 눌렸을 때
        lapButton.setOnClickListener{
            recordLapTime()
        }
        //리셋버튼이 눌렸을 때
        resetFab.setOnClickListener{
            reset()
        }
    }
    private fun pause(){ //타이머 정지 함수
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timerTask?.cancel() //타이머 취소
    }
    private fun start(){ //타이머 시작 함수
        fab.setImageResource(R.drawable.ic_baseline_pause_24)
        //안드로이드의 두 가지 스레드 모드가 있습니다.
        //메인스레드 (일반적인 UI 가능),워커스레드 (작업 시간이 오래 걸리고 화면에 표시되지 않음. 당연히 UI 조작을 불가)
        timerTask = timer(period=10){// 워커스레드 (UI조작 불가)
            time++
            val sec = time/100
            val milli = time%100
            runOnUiThread{ // 메인스레드 (UI조작 가능)
                //하지만 "현재 스레드가 UI 스레드가 아니면, 필요한 동작을 UI 스레드의 이벤트 큐로 전달한다"특정 동작을 UI 스레드에서 동작하도록 합니다. 만약 현재 스레드가 UI 스레드이면 그 동작은 즉시 수행됩니다.
                secTextView.text = "$sec"
                milliTextView.text="$milli"
            }
        }
    }
    private fun recordLapTime(){ //랩기록 함수
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"
        //맨위 랩타임 추가
        lapLayout.addView(textView, 0)
        lap++
    }
    private fun reset(){ //타이머 리셋함수
        timerTask?.cancel()
        //모든 변수 초기화
        time=0
        isRunning=false
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        secTextView.text="0"
        milliTextView.text="00"

        lapLayout.removeAllViews()
        lap = 1
    }
}