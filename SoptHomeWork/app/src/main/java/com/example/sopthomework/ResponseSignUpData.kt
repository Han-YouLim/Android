package com.example.sopthomework

data class ResponseSignUpData(
    val success: Boolean,
    val message: String,
    val data: SignUpData?
)

data class SignUpData(
    val user_nickname: String
)
