package com.example.nevigationmenutest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView. OnNavigationItemSelectedListener {
    lateinit var layout_drawer: DrawerLayout
    lateinit var btn_navi : ImageView
    lateinit var naviView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        naviView = findViewById(R.id.navi_view)
        layout_drawer = findViewById(R.id.layout_drawer) //layout_drawer 연결
        btn_navi= findViewById(R.id.btn_navi)
        //네비게이션 메뉴 imageView 클릭했을 때 네비게이션 바가 왼쪽에서부터 오른쪽 방향으로
        //밀려 나와야 한다. 이것에 대해 구현해보자
        btn_navi.setOnClickListener{
            //왼쪽으로 시작해서 layout_drawer를 열어주기
            layout_drawer.openDrawer(GravityCompat.START)
        }
        //(중요)이거 아래거 안하면 네비게이션 바가 open되서 메뉴를 클릭해도 실행 안됨. 이거 꼭 해줘야 함
        naviView.setNavigationItemSelectedListener(this) //네비게이션 아이템 클릭 속성을 부여!
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.access -> Toast.makeText(this, "접근성", Toast.LENGTH_SHORT).show()

            R.id.send -> Toast.makeText(this, "메세지", Toast.LENGTH_SHORT).show()

            R.id.email -> Toast.makeText(this, "이메일", Toast.LENGTH_SHORT).show()

        }
        layout_drawer.closeDrawers() //네비게이션 아이템이 선택되었을 때 layout_drawer가 닫혀지게 설정
        return false
    }

    override fun onBackPressed() { //백버튼 눌렀을 때 실행
        if (layout_drawer.isDrawerOpen(GravityCompat.START)){ //네비게이션 바가 열려있을 때
            layout_drawer.closeDrawers() //네비게이션 바 닫아주기
        }
        else{ //네비게이션 바가 닫혀있을 때
            super.onBackPressed() //일반 백버튼 실행
        }

    }
}