package com.example.sopthomework

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class RequestSignUpData(
    @SerializedName("email")
    val id: String,
    val password: String,
    val sex: String,
    val nickname: String,
    val phone: String,
    val birth : String
    )
