package com.example.sns_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
class LogInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEdtText: EditText
    private lateinit var passwordEdtText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitiy_log_in)
        // Initialize Firebase Auth
        auth = Firebase.auth
        val loginbtn=findViewById<Button>(R.id.checkbtn)
        val passwordresetbtn=findViewById<Button>(R.id.passwordresetbtn)
        passwordEdtText=findViewById<EditText>(R.id.passwordEdtText)
        emailEdtText=findViewById<EditText>(R.id.emailEdtText)
        loginbtn.setOnClickListener{
            login()
        }
        passwordresetbtn.setOnClickListener {
            val intent = Intent(this, PasswordResetActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }


    private fun login(){ //로그인
        val email = emailEdtText.text.toString()
        val password = passwordEdtText.text.toString()
        if(email.length !=0 && password.length!=0) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "로그인이 성공하였습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                } else {
                        // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }

                }
        }else{
            Toast.makeText(this, "각 란의 입력을 완료하여 주십시오.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
        finish()
        System.exit(1)
    }

}