package com.example.sopthomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sopthomework.databinding.ActivitySignInBinding
import com.example.sopthomework.ToSignUpContract
import com.example.sopthomework.User
import com.example.sopthomework.api.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignInBinding
    private var nname: String = "noname"
    private val signUpLauncher =
        registerForActivityResult(ToSignUpContract()){ result: User? ->
            binding.apply {
                edtId.setText(result?.githubId)
                edtpwd.setText(result?.password)
                nname = result?.name.toString()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val functionName = object{}.javaClass.enclosingMethod.name
        printlog(functionName)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val id = binding.edtId.text.toString()
            val pwd = binding.edtpwd.text.toString()
            val name = nname

            if(id.isBlank() || pwd.isBlank()){
                Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요!", Toast.LENGTH_SHORT).show()
            }else{
//                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
//                val nextIntent = Intent(this, HomeActivity::class.java)
//
//                nextIntent.putExtra("inputId", id)
//                nextIntent.putExtra("inputPwd", pwd)
//                nextIntent.putExtra("inputName", nname)
//                startActivity(nextIntent)

                val requestLoginData = RequestLoginData(id, pwd)
                val call: Call<ResponseLoginData> = ServiceCreator.soptService.postLogin(requestLoginData)
                call.enqueue(object : Callback<ResponseLoginData> {
                    override fun onResponse(
                        call: Call<ResponseLoginData>,
                        response: Response<ResponseLoginData>
                    ) {
                        if (response.isSuccessful){
                            val data = response.body()?.data
                            Toast.makeText(this@SignInActivity, data?.user_nickname, Toast.LENGTH_SHORT).show()
                            val nextIntent = Intent(this@SignInActivity, HomeActivity::class.java)

                            nextIntent.putExtra("inputId", id)
                            nextIntent.putExtra("inputPwd", pwd)
                            nextIntent.putExtra("inputName", name)
                            startActivity(nextIntent)
                        }else{
                            //에러가 난 경우
                            Toast.makeText(this@SignInActivity, "에러!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                        Log.d("NetworkTest", "error:$t")
                    }
                })
            }
        }

        binding.tvSignin.setOnClickListener {
            val toSignUpIntent = Intent(this, SignUpActivity::class.java)
            signUpLauncher.launch(toSignUpIntent)
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
        Log.d(funName, "${funName}함수가 SignInActivity에서 호출되었습니다.")
    }

}



