package com.example.sopthomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sopthomework.api.ServiceCreator
import com.example.sopthomework.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val functionName = object{}.javaClass.enclosingMethod.name
        printlog(functionName)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            val inputid = binding.edtId2.text.toString()
            val inputpwd = binding.edtPwd.text.toString()
            val inputname = binding.edtName.text.toString()
//            val result1= inputid.isBlank()
//            val result2=inputpwd.isBlank()
//            val result3=inputname.isBlank()
//            val result4 = inputid.isBlank() && inputpwd.isBlank() && inputname.isBlank()
//            Log.d("시발", "${result1} 그리고 ${result2} 그리고 ${result3} 그리고 ${result4}")

            if(inputid.isBlank() || inputpwd.isBlank() || inputname.isBlank()){
                Toast.makeText(this, "빈칸이 있는지 확인해주세요!", Toast.LENGTH_SHORT).show()
            }else{
                val requestSignUpData = RequestSignUpData(inputid, inputpwd, "0", inputname,"010-0000-0000", "2000-00-00")
                val call: Call<ResponseSignUpData> = ServiceCreator.soptServiceSignUp.postSignUp(requestSignUpData) //
                call.enqueue(object : Callback<ResponseSignUpData> {
                    override fun onResponse(
                        call: Call<ResponseSignUpData>,
                        response: Response<ResponseSignUpData>
                    ) {
                        if (response.isSuccessful){
                            val data = response.body()?.data
                            Toast.makeText(this@SignUpActivity, data?.user_nickname, Toast.LENGTH_SHORT).show()
                            val nextIntent = Intent(this@SignUpActivity, SignInActivity::class.java)

                            nextIntent.putExtra("inputId", inputid)
                            nextIntent.putExtra("inputPwd", inputpwd)
                            nextIntent.putExtra("inputName", inputname)
                            startActivity(nextIntent)
                        }else{
                            //에러가 난 경우
                            Toast.makeText(this@SignUpActivity, "에러!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                        Log.d("NetworkTest", "error:$t")
                    }
                })

//                  setResult(
//                      RESULT_OK,
//                      Intent().apply {
//                          putExtra(
//                              "SignedUser",
//                              User(
//                                  binding.edtId2.text.toString(),
//                                  binding.edtPwd.text.toString(),
//                                  binding.edtName.text.toString()
//                              )
//                          )
//                      })
//                  finish()
//                val nextIntent = Intent(this, SignInActivity::class.java)
//                nextIntent.putExtra("inputId", inputid)
//                nextIntent.putExtra("inputPwd", inputpwd)
//                nextIntent.putExtra("inputName", inputname)
//                startActivity(nextIntent)
            }
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
        Log.d(funName, "${funName}함수가 SignUpActivity에서 호출되었습니다.")
    }

}