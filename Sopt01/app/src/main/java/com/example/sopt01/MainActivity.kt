package com.example.sopt01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sopt01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //메모리
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //bindingClass의 inflate()를 호출해 해당 클래스에 접근가능하도록
        //inflate => 메모리를 객체화시킨다.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //헐
        initButtonClickEvent(binding)
    }

    //버튼 눌렀을 때
    private fun initButtonClickEvent(binding: ActivityMainBinding){
        val uid = binding.edtId.text.toString()
        val upwd = binding.edtPwd.text.toString()
        binding.buttonLogin.setOnClickListener {
            if(uid.isNullOrBlank() || upwd.isNullOrBlank()){
                Toast.makeText(this, "써라", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "얍 ${uid}, ${upwd}", Toast.LENGTH_SHORT).show()
                xmlTransfer()
            }
        }
    }
    //화면전환
    private fun xmlTransfer(){
        val intent = Intent(this, Test1::class.java)
        startActivity(intent)
    }
}