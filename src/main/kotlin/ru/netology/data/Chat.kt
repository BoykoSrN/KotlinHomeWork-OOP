package ru.netology.data

data class Chat(
    val id: Int?,
    val users: List<Int>,
    val messages: List<Message>
)