package com.example.studentmanagement

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*

class PersonalInfo : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var tvName: TextView
    lateinit var tvAge: TextView
    lateinit var tvTel: TextView
    lateinit var tvGender: TextView

    lateinit var str_name:String
    lateinit var str_gender:String
    var age:Int = 0
    lateinit var str_tel:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        tvName=findViewById(R.id.edtName)
        tvAge=findViewById(R.id.edtAge)
        tvGender=findViewById(R.id.edtgender)
        tvTel=findViewById(R.id.edtTel)

        val intent = intent
        str_name=intent.getStringExtra("intent_name").toString()

        dbManager = DBManager(this, "personalDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        var cursor:Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM personal WHERE NAME = '"+str_name+"';", null)
        if (cursor.moveToNext()) {
            str_gender = cursor.getString((cursor.getColumnIndex("gen"))).toString()
            age = cursor.getInt((cursor.getColumnIndex("age")))
            str_tel = cursor.getString((cursor.getColumnIndex("tel"))).toString()
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        tvName.text = str_name
        tvAge.text = ""+age
        tvGender.text = str_gender
        tvTel.text = str_tel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personal_info,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_home -> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_list -> {
                val intent = Intent(this,PersonalList::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_reg -> {
                val intent = Intent(this,PersonalReg::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_remove -> {
                dbManager = DBManager(this, "personalDB", null, 1)
                sqlitedb = dbManager.readableDatabase
                sqlitedb.execSQL("DELETE FROM personal WHERE NAME = '"+str_name+"';")
                sqlitedb.close()
                dbManager.close()
                val intent = Intent(this,PersonalList::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}