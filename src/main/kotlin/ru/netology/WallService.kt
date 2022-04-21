package ru.netology

class WallService {
    private var posts = emptyArray<Post>()
    private var postId = 1

    fun add(post: Post): Post {
        val newPost = post.copy(id = post.id + postId)
        posts += newPost
        postId++
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
}