package ru.netology.data

import ru.netology.Attachment
import java.time.LocalDate

data class Post (
    val id: Long,
    val ownerId: Long,
    val fromId: Long,
    val createdBy: Long,
    val date: LocalDate,
    val text: String,
    val replyOwnerId: Long,
    val replyPostId: Long,
    val friendsOnly: Boolean,
    val comments: Any?,
    val copyright: String?,
    val likes: Int = 0,
    val reposts: Int?,
    val views: Int?,
    val postType: String,
    val signerId: Long,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val donut: Boolean,
    val postponedId: Int,
    val attachments: ArrayList<Attachment>?
)