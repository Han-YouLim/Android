package com.example.flashlight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageSwitcher
import android.widget.Switch

class MainActivity : AppCompatActivity() {
    lateinit var flashSwitch:Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flashSwitch = findViewById(R.id.flashSwitch)
        val torch = Torch(this)
        val intent = Intent(this, TorchService::class.java)//이 액티비(객체)티에서 TorchService객체로

        flashSwitch.setOnCheckedChangeListener { buttonView, isChecked -> //스위치
            if(isChecked){ //체크 O
               // torch.flashOn() //서비스가 아닌 액티비티에서만 할 때
                   intent.action="on"
                   startService(intent) //서비스요청
            }else{
               // torch.flashOff()
                intent.action="off"
                startService(intent)
            }
        }
    }
}