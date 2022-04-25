package ru.netology

import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

class WallServiceTest {

    @Test
    fun addPost() {
        val service = WallService()
        val result = service.add(Post(0,
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
        ))

        assertNotEquals(0, result.id)
    }

    @Test
    fun updateExistingTrue() {
        val service = WallService()

        service.add(Post(0,
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
        ))

        val update = Post(1,
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

        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateExistingFalse() {
        val service = WallService()

        service.add(Post(0,
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
        ))

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

        val result = service.update(update)

        assertFalse(result)
    }
}