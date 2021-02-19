package com.example.studentmanagement

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class PersonalReg : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var btnRe:Button
    lateinit var edtName:EditText
    lateinit var edtAge:EditText
    lateinit var edtTel:EditText
    lateinit var gender:RadioGroup
    lateinit var male:RadioButton
    lateinit var female:RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_reg)

        btnRe = findViewById(R.id.btnRe)
        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        edtTel = findViewById(R.id.edtTel)
        gender = findViewById(R.id.gender)
        male = findViewById(R.id.male)
        female = findViewById(R.id.female)

        dbManager = DBManager(this, "personalDB", null, 1)

        btnRe.setOnClickListener {
            var str_name:String = edtName.text.toString()
            var str_age:String = edtAge.text.toString()
            var str_tel:String = edtTel.text.toString()
            var str_gender:String = ""

            if(gender.checkedRadioButtonId==R.id.male){
                str_gender = "male"
            }
            if(gender.checkedRadioButtonId==R.id.female){
                str_gender = "female"
            }

            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO personal VALUES('"+str_name+"', '"+str_gender+"',"+str_age+", '"+str_tel+"');")
            sqlitedb.close()

            val intent = Intent(this,PersonalInfo::class.java)
            intent.putExtra("intent_name", str_name)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personal_reg, menu)
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
        }
        return super.onOptionsItemSelected(item)
    }
}