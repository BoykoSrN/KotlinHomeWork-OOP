package ru.netology.service

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.netology.data.CommentPost
import ru.netology.data.Post
import java.time.LocalDate

class WallServiceTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        WallService.clear()
    }


    @Test
    fun addPost() {
        val expectedId: Long = 1
        val post = Post(
                0,
                0,
                0,
                0,
                LocalDate.now(),
                "_",
                0,
                0,
                true,
                true,
                null,
                0, 0,
                0,
                "post",
                0,
                true,
                true,
                true,
                false,
                false,
                false,
                false,
                0,
                null
        )

        val actualId = WallService.add(post)

        assertEquals(expectedId, actualId.id)
    }

    @Test
    fun updateExistingTrue() {

        WallService.add(
            Post(
                0,
                0,
                0,
                0,
                LocalDate.now(),
                "Привет",
                0,
                0,
                true,
                true,
                null,
                0,
                0,
                0,
                "post",
                0,
                true,
                true,
                true,
                false,
                false,
                false,
                false,
                0,
                null
            )
        )

        val result = WallService.update(Post(
            1 ,
            0,
            0,
            0,
            LocalDate.now(),
            "Пока",
            0,
            0,
            true,
            true,
            null,
            0,
            0,
            0,
            "post",
            0,
            true,
            true,
            true,
            false,
            false,
            false,
            false,
            0,
            null
        ))

        assertTrue(result)
    }

    @Test
    fun updateExistingFalse() {

        WallService.add(
            Post(
                0,
                0,
                0,
                0,
                LocalDate.now(),
                "Привет",
                0,
                0,
                true,
                true,
                null,
                0,
                0,
                0,
                "post",
                0,
                true,
                true,
                true,
                false,
                false,
                false,
                false,
                0,
                null
            )
        )

        val update = Post(
            3,
            0,
            0,
            0,
            LocalDate.now(),
            "Пока",
            0,
            0,
            true,
            true,
            null,
            0,
            0,
            0,
            "post",
            0,
            true,
            true,
            true,
            false,
            false,
            false,
            false,
            0,
            null
        )

        val result = WallService.update(update)

        assertFalse(result)
    }

    @Test(expected = WallService.PostNotFoundException::class)
    fun shouldThrow() {

        WallService.add(
            Post(
                0,
                0,
                0,
                0,
                LocalDate.now(),
                "Привет",
                0,
                0,
                true,
                true,
                null,
                0,
                0,
                0,
                "post",
                0,
                true,
                true,
                true,
                false,
                false,
                false,
                false,
                0,
                null
            )
        )

        WallService.createComment(
            CommentPost(
                1,
                1,
                2,
                LocalDate.now(),
                "Привет",
                false,
                null,
                null,
                null,
                null
            )
        )
    }

    @Test
    fun successfulExecutionCreateComment() {

        WallService.add(
            Post(
                0,
                0,
                0,
                0,
                LocalDate.now(),
                "Привет",
                0,
                0,
                true,
                true,
                null,
                0,
                0,
                0,
                "post",
                0,
                true,
                true,
                true,
                false,
                false,
                false,
                false,
                0,
                null
            )
        )

        val result = WallService.createComment(
            CommentPost(
                1,
                1,
                1,
                LocalDate.now(),
                "Привет",
                false,
                null,
                null,
                null,
                null
            )
        )

        assertEquals(Unit, result)
    }
}