package com.example.sopthomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sopthomework.databinding.ActivityHomeBinding
import com.example.sopthomework.databinding.ActivitySignInBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val functionName = object{}.javaClass.enclosingMethod.name
        printlog(functionName)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fromSignInIntent: Intent = getIntent()
        if(fromSignInIntent.hasExtra("inputId") && fromSignInIntent.hasExtra("inputName")) {
            binding.tvGithubid.setText(fromSignInIntent.getStringExtra("inputId"))
            binding.tvNameInHome.setText(fromSignInIntent.getStringExtra("inputName"))
            binding.tvInro.setText("동에 번쩍 서에 번쩍! 하는 개발자")
        }

        binding.btnMore.setOnClickListener {
            val repoFragment = Fragment_repo()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.repo_list_fragment, repoFragment)
            transaction.commit()
        }

    }

    override fun onStart() {
        super.onStart()
        val functionName = object{}.javaClass.enclosingMethod.name
        printlog(functionName)

    }
    override fun onResume() {
        super.onResume()
        val functionName = object{}.javaClass.enclosingMethod.name
        printlog(functionName)
    }

    override fun onPause() {
        super.onPause()
        val functionName = object{}.javaClass.enclosingMethod.name
        printlog(functionName)
    }

    override fun onStop() {
        super.onStop()
        val functionName = object{}.javaClass.enclosingMethod.name
        printlog(functionName)
    }

    override fun onDestroy() {
        super.onDestroy()
        val functionName = object{}.javaClass.enclosingMethod.name
        printlog(functionName)
    }

    fun printlog(funName:String){
        Log.d(funName, "${funName}함수가 HomeActivity에서 호출되었습니다.")
    }
}