package com.example.flashlight

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TorchService : Service() {

    private  val torch: Torch by lazy { //var이면  by lazy는 val만 선언해야함(lateinit 은 var에서만 사용)
       Torch(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       when(intent?.action){
           "on" ->{
                torch.flashOn()
           }
           "off" ->{
               torch.flashOff()
           }
       }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder { //바운드 서비스 메소드(여기선 사용x)
        TODO("Return the communication channel to the service.")
    }
}