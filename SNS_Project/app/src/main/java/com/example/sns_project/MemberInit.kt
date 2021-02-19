package com.example.sns_project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
class MemberInit : AppCompatActivity() {
    private val Tag : String = "MemberInit.kt"
    private lateinit var auth: FirebaseAuth
    private lateinit var nameEdtText: EditText
    private lateinit var phoneEdtText: EditText
    private lateinit var addressEdtText: EditText
    private lateinit var birthdayEdtText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_init)
        // Initialize Firebase Auth
        val checkbtn=findViewById<Button>(R.id.checkbtn)

        nameEdtText=findViewById<EditText>(R.id.nameEdtText)
        phoneEdtText=findViewById<EditText>(R.id.PhonenumbereditText)
        addressEdtText=findViewById<EditText>(R.id.addresseditText)
        birthdayEdtText=findViewById<EditText>(R.id.birthdayeditText)

        checkbtn.setOnClickListener{
            profileUpdate()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
        finish()
        System.exit(1)
    }

    private fun profileUpdate(){
        val name = nameEdtText.text.toString()
        val phonenumber = phoneEdtText.text.toString()
        val birthday = birthdayEdtText.text.toString()
        val address = addressEdtText.text.toString()

        if(name.length >0 && phonenumber.length > 9 && birthday.length>5 && address.length>0) {
            val user = Firebase.auth.currentUser //uid
            // Access a Cloud Firestore instance from your Activity
            val db = Firebase.firestore

            val memberInfo = MemberInfo(name, phonenumber,birthday, address)
            if (user != null) {
                db.collection("users").document(user.uid).set(memberInfo)
                        .addOnSuccessListener { Log.d(Tag, "DocumentSnapshot successfully written!")
                            Toast.makeText(this, "사용자 정보 입력에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        .addOnFailureListener { e -> Log.w(Tag, "Error writing document", e)
                            Toast.makeText(this, "사용자 정보 입력에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        }
            }

            /*
            //홈페이지 코드 에러나서 고침 ***********
            val profileUpdates = UserProfileChangeRequest.Builder().apply {
                displayName = name
            }.build()
            if(user != null){
                user.updateProfile(profileUpdates).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "사용자 정보 입력에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }*/
        }else{
            Toast.makeText(this, "각 란의 입력을 완료하여 주십시오.", Toast.LENGTH_SHORT).show()
        }
    }

}