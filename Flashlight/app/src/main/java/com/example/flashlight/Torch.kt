package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class Torch(context: Context) { //context(앱전체의 정보를 갖고 있음)를 통해 cameraManager(카메라 제어)을 얻을 수 있음
    private var cameraId:String? = null //카메라 객체 아이디
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    init { //클래스 초기설정
        cameraId = getCameraId()
    }

    fun flashOn(){ //제어는 cameraManager
        cameraId?.let { cameraManager.setTorchMode(it, true) }
    }
    fun flashOff(){
        cameraId?.let { cameraManager.setTorchMode(it, false)}
    }

    private fun getCameraId():String?{
        val camereIds = cameraManager.cameraIdList //카메라 아이디 리스트 가져오기
        for (id in camereIds){
            val info = cameraManager.getCameraCharacteristics(id)
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)//플레시 사용여부 확인
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING) //렌즈 방향
            if(flashAvailable !== null && flashAvailable && lensFacing !== null&& lensFacing == CameraCharacteristics.LENS_FACING_BACK){
            //플레시가 NULL이 아니여야하고, 참이여야 하며, 렌즈 방향이 NULL이 아니여야 하고, 렌즈방향이 후면이어야 한다.
                return id
            }

        }
        return null
    }

}