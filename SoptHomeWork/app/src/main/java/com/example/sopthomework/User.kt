package com.example.sopthomework

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val githubId: String,
    val password: String,
    val name: String
) : Parcelable