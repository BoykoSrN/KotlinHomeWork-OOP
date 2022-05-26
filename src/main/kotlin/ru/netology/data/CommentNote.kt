package ru.netology.data

data class CommentNote(
    val id: Int,
    val noteId: Int,
    val ownerId: Int,
    val visible: Boolean,
    val noteText: String
)