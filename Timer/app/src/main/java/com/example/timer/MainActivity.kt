package com.example.timer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time=0
    private var timerTask: Timer?= null
    private var isRunning = false //play=true, pause=false

    lateinit var secTextView: TextView
    lateinit var milliTextView: TextView
    lateinit var timerSettingButton: Button
    lateinit var inputEditText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        secTextView=findViewById<TextView>(R.id.secTextView)
        milliTextView=findViewById<TextView>(R.id.milliTextView)
        timerSettingButton=findViewById<Button>(R.id.timerSettingButton)
        inputEditText=findViewById<EditText>(R.id.inputEditText)

        timerSettingButton.setOnClickListener{
            if(inputEditText.text.toString().toInt() != 0){
                //timer (타이머)
                //안드로이드의 두 가지 스레드 모드가 있습니다.
                //메인스레드 (일반적인 UI 가능)
                //워커스레드 (작업 시간이 오래 걸리고 화면에 표시되지 않음. 당연히 UI 조작을 불가)
                time=inputEditText.text.toString().toInt()*100  //100 곱하는거 잊지 말귀
                timerTask = timer(period = 10) {
                    // 워커스레드 (UI조작 불가)
                    time--
                    val sec = time / 100
                    val milli = time % 100
                    runOnUiThread { // 메인스레드 (UI조작 가능)
                        //"특정 동작을 UI 스레드에서 동작하도록 합니다. 만약 현재 스레드가 UI 스레드이면 그 동작은 즉시 수행됩니다."
                        //하지만 "현재 스레드가 UI 스레드가 아니면, 필요한 동작을 UI 스레드의 이벤트 큐로 전달한다
                        secTextView.text = "$sec"
                        milliTextView.text = "$milli"
                    }
                    if (time==0) { //0일 때 타이머 멈추기 또는 조건을 sec==0&&milli==0으로
                        runOnUiThread {
                            secTextView.text = "0"
                            milliTextView.text = "0"
                            timerTask?.cancel() //타이머 취소
                        }
                    }
                }
            }
        }
    }
}
