package ru.netology.attachment

import java.time.LocalDate

data class Photo(
    val id: Int,
    val width: Int,
    val height: Int,
    val date: LocalDate,
    val text: String,
    val userId: Int,
    val albumId: Int,
    val ownerId: Int,
    val url: String
)