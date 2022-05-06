package ru.netology.attachment

abstract class Attachment {
    abstract val type: String
}

data class AudioAttachment(
    val audio: Audio
) : Attachment() {
    override val type: String = "audio"
}

data class VideoAttachment(
    val video: Video
) : Attachment() {
    override val type: String = "video"
}

data class PhotoAttachment(
    val photo: Photo
) : Attachment() {
    override val type: String = "photo"
}

data class DocumentAttachment(
    val document: Document
) : Attachment() {
    override val type: String = "document"
}

data class LinkAttachment(
    val link: Link
) : Attachment() {
    override val type: String = "link"
}