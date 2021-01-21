package com.example.mygallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private val REQUEST_READ_EXTERNAL_STORAGE = 1000
    lateinit var viewPager:ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                var dlg = AlertDialog.Builder(this)
                dlg.setTitle("권한이 필요한 이유")
                dlg.setMessage("사진정보를 얻기 위해서는 외부저장소 권한이 꼭 필요합니다.")
                dlg.setPositiveButton("확인"){dialog, which -> ActivityCompat.requestPermissions(this@MainActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_READ_EXTERNAL_STORAGE)} //최대 몇개까지 가져오는지
                dlg.setNegativeButton("취소",null)
                dlg.show()
            }else{
                ActivityCompat.requestPermissions(this@MainActivity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_READ_EXTERNAL_STORAGE)
            }
        }else{
            getAllPhotos()
        }

    }

    private fun getAllPhotos(){
        //모든 사진 정보 가져오기
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, //가져올 데이터
            arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media.TITLE),// , //가져올 항목의 데이터
        null, //데이터를 가져올 조건
        null, //조건
        MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")//최신순
        var fragments = ArrayList<Fragment>()
        if (cursor != null){
            while(cursor.moveToNext()){
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                Log.d("MainActivity", uri)
                fragments.add(PhotoFragment.newInstance(uri))
            }
            cursor.close()
        }

        val adapter = MyPagerAdapter(supportFragmentManager)
        adapter.updateFragments(fragments)
        viewPager.adapter = adapter


        //3초마다 자동슬라이드
        timer(period =  3000){
            runOnUiThread{
                if (viewPager.currentItem < adapter.count-1){
                    viewPager.currentItem++
                }else{
                    viewPager.currentItem=0
                }
            }
        }
    }
}