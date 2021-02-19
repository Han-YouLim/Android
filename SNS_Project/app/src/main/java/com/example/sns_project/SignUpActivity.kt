package com.example.sns_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEdtText: EditText
    private lateinit var passwordEdtText: EditText
    private lateinit var passwordCheckEdtText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitiy_sign_up)
        // Initialize Firebase Auth
        auth = Firebase.auth
        val signupbtn=findViewById<Button>(R.id.signupbtn)
        val gotologinbtn=findViewById<Button>(R.id.gotologinbtn)
        passwordEdtText=findViewById<EditText>(R.id.passwordEdtText)
        emailEdtText=findViewById<EditText>(R.id.emailEdtText)
        passwordCheckEdtText = findViewById<EditText>(R.id.passwordCheckEdtText)
        signupbtn.setOnClickListener {
            Log.d("클릭", "클릭")
            signup()
        }

        gotologinbtn.setOnClickListener{
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signup(){ //회원가입
        val email = emailEdtText.text.toString()
        val password = passwordEdtText.text.toString()
        val passwordcheck = passwordCheckEdtText.text.toString()
        if(email.length !=0 && password.length!=0 && passwordcheck.length!=0) {
            if (password.equals(passwordcheck)) { //비밀번호확인 == 비밀번호
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입이 성공적으로 되었습니다.", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        //ui
                    } else {
                        // If sign in fails, display a message to the user.
                            if (task.exception != null) {
                                Toast.makeText(this, "createUserWithEmail:failure", Toast.LENGTH_SHORT).show()
                            }
                            //ui
                        }
                }
            } else {
                Toast.makeText(this, "비밀번호가 동일하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "각 란의 입력을 완료하여 주십시오.", Toast.LENGTH_SHORT).show()
        }
    }
    //로그아웃 후 회원가입 창에서 뒤로가기 눌렀을 때
    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
        finish()
        System.exit(1)
    }

}