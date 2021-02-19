package com.example.sns_project
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
class PasswordResetActivity  : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEdtText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)
        // Initialize Firebase Auth
        auth = Firebase.auth
        val sendbtn=findViewById<Button>(R.id.checkbtn)
        emailEdtText=findViewById<EditText>(R.id.emailEdtText)
        sendbtn.setOnClickListener{
            send()
        }
    }
    private fun send(){
        val email = emailEdtText.text.toString()
        if(email.length !=0) {
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "이메일을 보냈습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this, "입력을 완료하여 주십시오.", Toast.LENGTH_SHORT).show()
        }
    }

}