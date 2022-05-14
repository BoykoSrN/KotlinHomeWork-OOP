package ru.netology.data

data class Note(
    val id: Int,
    val ownerId: Int,
    val text: String,
    val visible: Boolean
)