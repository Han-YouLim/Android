package com.example.sopthomework.api

import com.example.sopthomework.RequestSignUpData
import com.example.sopthomework.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptServiceSignUp {
    @POST("/login/signup")
    fun postSignUp(
        @Body body: RequestSignUpData
    ) : Call<ResponseSignUpData>
}