package ru.netology.attachment

import java.time.LocalDate

data class Document(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val url: String,
    val size: Int,
    val ext: String,
    val date: LocalDate
)