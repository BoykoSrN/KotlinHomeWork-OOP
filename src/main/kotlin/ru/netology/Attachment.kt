package ru.netology


abstract class Attachment(val type: String)

data class AudioAttachment(
    val id: Int,
    val ownerId: Int,
    val albumId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val date: Int,
    val url: String,
) : Attachment("audio")

data class VideoAttachment(
    val id: Int,
    val ownerId: Int,
    val albumId: Int,
    val title: String,
    val duration: Int,
    val date: Int,
    val url: String
) : Attachment("video")

data class PhotoAttachment(
    val id: Int,
    val ownerId: Int,
    val userId: Int,
    val albumId: Int,
    val date: Int,
    val width: Int,
    val height: Int,
    val url: String
) : Attachment("photo")

data class DocumentAttachment(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String,
    val date: Int,
    val url: String
) : Attachment("doc")

data class LinkAttachment(
    val url: String,
    val title: String
) : Attachment("link")