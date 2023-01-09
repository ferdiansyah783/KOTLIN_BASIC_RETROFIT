package com.kotlin.retrrofit.response

data class PostResponse (
    val userId: Int,
    val id: Int,
    val title: String?,
    val body: String?,
)