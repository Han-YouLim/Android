package com.example.groupapp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() { //한유림
    lateinit var myHelper: myDBHelper //myDBHelper클래스(SQLiteOpenHelper상속)의 인스턴스(내부 클래스임)
    lateinit var sqlDB:SQLiteDatabase //SQLiteDatabase클래스의 인스턴스
    lateinit var edtName: EditText
    lateinit var edtNumber: EditText
    lateinit var edtNameResult:EditText
    lateinit var edtNumberResult: EditText
    lateinit var btnInit:Button
    lateinit var btnInsert: Button
    lateinit var btnSelect:Button
    lateinit var btnModifi:Button
    lateinit var btnDel:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName=findViewById(R.id.edtName)
        edtNumber=findViewById(R.id.edtNumber)
        edtNameResult=findViewById(R.id.edtNameResult)
        edtNumberResult=findViewById(R.id.edtNumberResult)

        btnInit=findViewById(R.id.btnInit)
        btnInsert=findViewById(R.id.btnInsert)
        btnSelect=findViewById(R.id.btnSelect)
        btnModifi=findViewById(R.id.btnModification)
        btnDel=findViewById(R.id.btnDel)

        myHelper = myDBHelper(this)//myDBHelper인스턴스 만들기

        btnInit.setOnClickListener{
            sqlDB = myHelper.writableDatabase //getWritableDatabase, get 생략가능=> 앞 myHelper에 밑줄이 있는데 이것은 get이나 set을 생략할 수 있다는 뜻
            myHelper.onUpgrade(sqlDB, 1,2)
            sqlDB.close()
            Toast.makeText(applicationContext,"초기화됨", Toast.LENGTH_SHORT).show()
            btnSelect.callOnClick() //조회버튼 누른것 처럼
        }

        btnInsert.setOnClickListener {
            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '"+edtName.text.toString()+"' , "
                    +edtNumber.text.toString() + ");")
            sqlDB.close()
            Toast.makeText(applicationContext,"입력됨", Toast.LENGTH_SHORT).show()
            btnSelect.callOnClick() //조회버튼 누른것 처럼
        }

        btnSelect.setOnClickListener {
            sqlDB=myHelper.readableDatabase
            var cursor:Cursor
            cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;",null)
            var strNames = "그룹 이름"+"\r\n"+"---------"+"\r\n"
            var strNumber = "인원"+"\r\n"+"---------"+"\r\n"

            while (cursor.moveToNext()){
                strNames+=cursor.getString(0)+"\r\n"
                strNumber+=cursor.getString(1)+"\r\n"
            }

            edtNameResult.setText(strNames) //아래 TextView에 출력
            edtNumberResult.setText(strNumber)
            Toast.makeText(applicationContext,"조회됨", Toast.LENGTH_SHORT).show()

            cursor.close() //close the cursor
            sqlDB.close() //close the DB
        }

        btnModifi.setOnClickListener {
            sqlDB=myHelper.writableDatabase
            sqlDB.execSQL("UPDATE groupTBL SET gNumber = "+edtNumber.text+" WHERE gName= '"+edtName.text.toString()+"';")
            sqlDB.close()
            btnSelect.callOnClick() //조회버튼 누른것 처럼
            Toast.makeText(applicationContext,"수정됨", Toast.LENGTH_SHORT).show()
        }

        btnDel.setOnClickListener {
            sqlDB=myHelper.writableDatabase
            sqlDB.execSQL("DELETE FROM groupTBL WHERE gName='"+edtName.text.toString()+"';")
            Toast.makeText(applicationContext,"삭제됨", Toast.LENGTH_SHORT).show()
            sqlDB.close()
            btnSelect.callOnClick() //조회버튼 누른것 처럼
        }
    }

    inner class myDBHelper(context: Context): SQLiteOpenHelper(context, "groupDB", null,1){
        override fun onCreate(db: SQLiteDatabase?) { //Create Table
            db!!.execSQL("CREATE TABLE groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);") //칼럼 이름: gName, 데이터 형식: CHAR(20), PRIMARY KEY
            //칼럼이름: gNumber 데이터 형식: INTEGER
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { //Update Table
            db!!.execSQL("DROP TABLE IF EXISTS groupTBL") //이미 groupTBL이 있으면 삭제
            onCreate(db) //그 후 다시 생성
        }
    }
}