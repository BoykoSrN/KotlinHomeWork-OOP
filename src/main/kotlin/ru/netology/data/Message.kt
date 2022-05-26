package ru.netology.data

data class Message(
    val id: Int,
    val ownerId: Int,
    var message: String?,
    var isRead: Boolean = false
)