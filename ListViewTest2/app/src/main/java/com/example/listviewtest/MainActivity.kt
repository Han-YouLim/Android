package com.example.listviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var userList = arrayListOf<User>(
        User(R.drawable.crossplatform, "한유림", 22, "안녕하세여"),
        User(R.drawable.crossplatform, "감자", 21, "알로아"),
        User(R.drawable.crossplatform, "고구마", 23, "고구고구"),
        User(R.drawable.crossplatform, "당근", 25, "당근당근"),
        User(R.drawable.crossplatform, "밤", 32, "나이트~"),
        User(R.drawable.crossplatform, "치즈", 52, "치이~즈~"),
        User(R.drawable.crossplatform, "피자", 32, "먹고싶어욤")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView: ListView = findViewById(R.id.listView)

//        val items = arrayOf("사과", " 당근", "대파", "오이")
//        //context란 한 액티비티의 정보를 담고 있다.
//        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = UserAdapter(this, userList)
        listView.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            //현재 클릭된 포지션을 리턴하여 선택된 아이템을 모델형태로 알 수 있다.
            val selectItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()
        }
    }
}