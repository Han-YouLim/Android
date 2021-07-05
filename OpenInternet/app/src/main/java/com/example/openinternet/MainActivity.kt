package com.example.openinternet

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.openinternet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.open.setOnClickListener {
            val address = binding.edtInternetAddtess.text.toString()
            //editText의 text는 editable이다. --> string과는 다른 타입이므로 toString()으로 형변환 필요
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address)) //암시적 intent
            startActivity(intent)
        }

        //이 함수로 editText의 변화를 살펴볼 수 있음
       binding.edtInternetAddtess.addTextChangedListener(object : TextWatcher{
           //변환 후
            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

           //텍스트가 변화하기 전
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }
        })

    }
}