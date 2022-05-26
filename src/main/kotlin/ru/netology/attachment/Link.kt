package ru.netology.attachment

data class Link(
    val url: String,
    val title: String,
    val caption: String,
    val description: String,
    val photo: Photo
)