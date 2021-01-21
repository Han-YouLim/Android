//package com.example.mywebbrowser
//
//import android.content.Intent
//import android.net.Uri
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.ContextMenu
//import android.view.Menu
//import android.view.MenuItem
//import android.view.View
//import android.view.inputmethod.EditorInfo
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.EditText
//
//class MainActivity : AppCompatActivity() {
//    lateinit var webView: WebView
//    lateinit var urlEditText:EditText
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        urlEditText = findViewById(R.id.urlEditText)
//        webView = findViewById(R.id.webView)
//
//        //웹 뷰 기본 설정
//        webView.apply{
//            settings.javaScriptEnabled = true
//            webViewClient = WebViewClient()
//        }
//
//        webView.loadUrl("http://www.google.com")
//
//        urlEditText.setOnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH){ //검색버튼이 눌렸을 때
//                webView.loadUrl(urlEditText.text.toString())
//                true
//            }else{
//                false
//            }
//        }
//        //컨텍스트 메뉴를 사용할 위젯을 등록
//        registerForContextMenu(webView)
//
//    }
//    //뒤로가기 버튼 눌렸을 때
//    override fun onBackPressed() {
//        if(webView.canGoBack()){
//            webView.goBack()
//        }else{
//            super.onBackPressed()
//        }
//    }
//    //옵션 메뉴 표시
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//    //옵션 메뉴 이벤트 처리
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item?.itemId){
//            //구글옵션이나 홈버튼 클릭했을 때
//            R.id.action_google, R.id.action_home -> {
//                webView.loadUrl("http://www.google.com")
//                return true
//            }
//            //네이버를 클릭했을 때
//            R.id.action_naver-> {
//                webView.loadUrl("http://www.naver.com")
//                return true
//            }
//            //다음
//            R.id.action_daum -> {
//                webView.loadUrl("http://www.daum.com")
//                return true
//            }
//            //개발자 전화->전화앱 실행(암시적 인텐트)
//            R.id.action_call -> {
//                val intent = Intent(Intent.ACTION_DIAL)
//                intent.data = Uri.parse("tel:031-123-4567")
//                if(intent.resolveActivity(packageManager) != null){
//                    startActivity(intent)
//                }
//                return true
//            }
//            //개발자 문자
//            R.id.action_send_text -> {
//                val intent = Intent(Intent.ACTION_SENDTO)
//                intent.data = Uri.parse("smsto:"+Uri.encode("012-3456-7890"))
//                if(intent.resolveActivity(packageManager) != null){
//                    startActivity(intent)
//                }
//                return true
//            }
//            //개발자 이메일
//            R.id.action_email -> {
//                val intent = Intent(Intent.ACTION_SENDTO)
//                intent.data = Uri.parse("mailto:example@example.com")
//                if(intent.resolveActivity(packageManager) != null){
//                    startActivity(intent)
//                }
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//    //컨텍스트 메뉴 작성
//    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//        menuInflater.inflate(R.menu.context, menu)
//    }
//    //컨텍스트 메뉴 이벤트 처리
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        when(item?.itemId){
//            R.id.action_share -> { //페이지 공유
//                val intent = Intent(Intent.ACTION_SEND)
//                intent.setType("text/plain")
//                intent.putExtra(Intent.EXTRA_TEXT, webView.url.toString())
//                val shareIntent = Intent.createChooser(intent,null)
//                startActivity(shareIntent)
//                return true
//            }
//            R.id.action_browser ->{ //기본 웹브라우저에서 열기
//                val intent = Intent(Intent.ACTION_VIEW,Uri.parse(webView.url))
//                startActivity(Intent.createChooser(intent,null))
//                return true
//            }
//        }
//        return super.onContextItemSelected(item)
//    }
//}