package ru.netology.attachment

import java.time.LocalDate

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val date: LocalDate,
    val url: String
)