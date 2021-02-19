package com.example.listviewtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

//어뎁터 만들기! 리스트뷰는 반드시 어뎁터로 연결을 해주어야한다.
class UserAdapter(val context: Context, val userList: ArrayList<User>) : BaseAdapter() {
    override fun getCount(): Int {
        return userList.size
    }

    override fun getItem(position: Int): Any {
        return userList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //뷰 붙이기
        val view:View = LayoutInflater.from(context).inflate(R.layout.list_item_user, null)
        val profile = view.findViewById<ImageView>(R.id.id_profile)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val greet = view.findViewById<TextView>(R.id.tv_greet)
        val age = view.findViewById<TextView>(R.id.tv_age)

        val user = userList[position]
        profile.setImageResource(user.profile)//이미지 경로를 user.profile에 삽입
        name.text = user.name
        age.text = user.age.toString()
        greet.text = user.greet

        //필수
        return view
    }
}