package com.kotlin.retrrofit

data class CreatePostResponse (
    val userId: Int?,
    val id: Int,
    val title: String?,
    val body: String?,
)