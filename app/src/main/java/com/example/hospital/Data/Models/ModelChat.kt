package com.example.chatapp

data class Chat(
    val type: String,
    val message: String,
    var isSelf: Boolean = false
)