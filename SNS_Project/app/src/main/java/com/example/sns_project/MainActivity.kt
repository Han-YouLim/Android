package com.example.sns_project

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var logoutButton: Button
    val db = Firebase.firestore
    private val Tag : String = "MainActivity.kt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logoutButton= findViewById(R.id.logoutButton)
        val user = Firebase.auth.currentUser

        if ( user== null){ //회원가입이 안되었을 때
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }else{
            val docRef = db.collection("users").document(user.uid)
            docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            if(document.exists()){
                                Log.d(Tag, "DocumentSnapshot data: ${document.data}")
                            }else{
                                Log.d(Tag, "No such user's document")
                                val intent = Intent(this, MemberInit::class.java)
                                startActivity(intent)
                            }
                        } else {
                            Log.d(Tag, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(Tag, "get failed with ", exception)
                    }

            /*
            user?.let {
                for (profile in it.providerData) {
                    val name = profile.displayName
                    if(name != null){
                        if(name.length == 0){
                            val intent = Intent(this, MemberInit::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                        }
                    }
                }
            }*/
        }

        logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
        finish()
        System.exit(1)
    }

}