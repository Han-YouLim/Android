package com.example.sopthomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopthomework.databinding.ActivityForFragmentBinding

class ForFragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val followingListFragment = BlankFragment() //onCreateView가 실행-> view 객체가 반환됨
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.user_info_fragment, followingListFragment)
        transaction.commit()
    }
}