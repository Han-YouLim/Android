package com.example.tiltsentor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {
    //**커스텀뷰를 화면에 배치
    private lateinit var tiltView: TiltView

    //센서 사용 준비
    private val sensorManager by lazy{ //센서매니저 객체 얻기
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        //화면이 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        //화면 가로모드 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)


        //**커스텀뷰를 화면에 배치
        tiltView = TiltView(this)
        setContentView(tiltView)
    }
    //센서 등록
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, //센서 종류 등 을 등록
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),//센서종류 지정
                SensorManager.SENSOR_DELAY_NORMAL)//얼마나 자주 센서값을 받을지
    }
    //센서값이 변경되면 호출 => 센서값읽어올 때 사용
    override fun onSensorChanged(event: SensorEvent?) {
        //values[0] : x축값 : 위로기울이면 -10~0, 아래로 기울이면 0~10
        //values[1] : y축값 : 왼쪽으로 기울이면 -10~0, 오른쪽으로 기울이면 0~10
        //values[2] : z축값 : 미사용
        event?.let {
            //화면을 가로로 돌렸으므로 x축 values와 y축 values값을 바꿈
            Log.d("MainActivity", "onSensorChanged: x:"+ "${event.values[1]}, y:${event.values[0]}, z:${event.values[2]} ")
            tiltView.onSensorEvent((event))
        }
    }
    //센서 정밀도가 변경되면 호출
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //TODO("Not yet implemented")
    }
    //센서 해제
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}