package ru.netology

import java.time.LocalDate

data class Comment(
    val id: Long,
    val fromId: Long,
    val postId: Long,
    val date: LocalDate,
    val text: String,
    val donut: Boolean,
    val replyToUser: Long?,
    val replyToComment: Long?,
    val attachments: ArrayList<Attachment>?,
    val thread: Any?
)
