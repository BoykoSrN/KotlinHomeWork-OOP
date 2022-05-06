package ru.netology.service

import ru.netology.data.Comment
import ru.netology.data.Post


object WallService {
    private var posts = mutableListOf<Post>()
    private var comments = mutableListOf<Comment>()
    private var postId = 0

    fun add(post: Post): Post {
        postId++
        val newPost = post.copy(id = postId.toLong())
        posts.add(newPost)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index,postUpdate) in posts.withIndex()) {
            if (postUpdate.id == post.id) {
                posts[index] = post.copy(
                    ownerId = post.ownerId + 1,
                    fromId = post.fromId + 1,
                    createdBy = post.createdBy + 1,
                    replyOwnerId = post.replyOwnerId + 1,
                    replyPostId = post.replyPostId + 1,
                    signerId = post.signerId + 1,
                    postponedId = post.postponedId + 1
                )
                return true
            }
        }
        return false
    }

    fun createComment(comment: Comment) {
        for (post in posts) {
            if (post.id == comment.postId) {
                comments.add(comment)
                return
            }
        }
        throw PostNotFoundException("No post with id ${comment.postId}")
    }

    class PostNotFoundException(message: String): RuntimeException(message)

    fun clear() {
        postId = 0
        posts.clear()
        comments.clear()
    }

}