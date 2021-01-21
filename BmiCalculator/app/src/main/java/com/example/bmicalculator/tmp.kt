//package com.example.bmicalculator
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//
//class MainActivity : AppCompatActivity() {  //한유림
//    lateinit var resultButton: Button //
//    lateinit var heightEditText: EditText//
//    lateinit var weightEditText: EditText//
//    lateinit var nameEditText: EditText
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        resultButton = findViewById<Button>(R.id.resultButton)//
//        heightEditText = findViewById<EditText>(R.id.heightEditText)
//        weightEditText = findViewById<EditText>(R.id.weightEditText)
//        nameEditText=findViewById<EditText>(R.id.nameEditText)
//
//        loadData()
//
//        resultButton.setOnClickListener{ //
//            saveData(heightEditText.text.toString().toInt(),
//                    weightEditText.text.toString().toInt() ,
//                    nameEditText.text.toString())
//
//            var intent = Intent(this, ResultActivity::class.java)
//            intent.putExtra("height", heightEditText.text.toString()) //height키에 키값 넣어주기
//            intent.putExtra("weight", weightEditText.text.toString()) //weight키에 키값 넣어주기
//            intent.putExtra("name", nameEditText.text.toString())
//            startActivity(intent)
//        }
//    }
//
//    private fun saveData(height:Int, weight:Int, name:String){  //데이터 저장
//        var pref = this.getPreferences(0)
//        var editor = pref.edit()
//
//        editor.putInt("KEY_HEIGHT", heightEditText.text.toString().toInt()).apply()
//        editor.putInt("KEY_WEIGHT", weightEditText.text.toString().toInt()).apply()
//        editor.putString("KEY_NAME", nameEditText.text.toString()).apply()
//    }
//
//    private fun loadData(){ //데이터 로드
//        var pref = this.getPreferences(0)//this=현재의 액티비티, 모드=0은 기본이라는 뜻
//        var height = pref.getInt("KEY_HEIGHT",0)
//        var weight = pref.getInt("KEY_WEIGHT",0)
//        var name = pref.getString("KEY_NAME","") //모드가 문자열이라 공백임
//        if(height !=0 && weight!=0 && name!=""){
//            heightEditText.setText(height.toString())
//            weightEditText.setText(weight.toString())
//            nameEditText.setText(name.toString())
//        }
//    }
//}