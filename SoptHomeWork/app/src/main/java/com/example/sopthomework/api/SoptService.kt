package com.example.sopthomework.api
import com.example.sopthomework.RequestLoginData
import com.example.sopthomework.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
interface SoptService {
    // /login/signin이란 식별자에 해당하는 데이터를 body에 담아 보낸다.
    @POST("/login/signin")
    fun postLogin(
        @Body body: RequestLoginData
    ) : Call<ResponseLoginData>
}