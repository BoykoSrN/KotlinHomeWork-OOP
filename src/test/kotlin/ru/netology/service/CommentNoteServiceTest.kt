package ru.netology.service

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import ru.netology.data.CommentNote
import ru.netology.data.Note

class CommentNoteServiceTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        CommentNoteService.clearComments()
    }

    @Test
    fun addComment() {
        val expectedId = 1

        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        val actualId = CommentNoteService.add(
            CommentNote(
            0,
            1,
            1,
            true,
            "___"
        )
        )

        assertEquals(expectedId, actualId)
    }

    @Test(expected = NoteNotFoundException::class)
    fun throwIdAddComment() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            1,
            2,
            1,
            true,
            "___"
        )
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun throwVisibleAddComment() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            false
        )
        )

        CommentNoteService.add(
            CommentNote(
            1,
            1,
            1,
            true,
            "___"
        )
        )
    }


    @Test
    fun updateComment() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            0,
            1,
            1,
            true,
            "___"
        )
        )

        val result = CommentNoteService.update(
            CommentNote(
                1,
                1,
                1,
                true,
                "___+++"
            )
        )

        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun throwIdUpdateComment() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            0,
            1,
            1,
            true,
            "___"
        )
        )

        CommentNoteService.update(
            CommentNote(
            2,
            1,
            1,
            true,
            "___+++"
        )
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun throwVisibleUpdateComment() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            1,
            1,
            1,
            false,
            "___"
        )
        )

        CommentNoteService.update(
            CommentNote(
            2,
            1,
            1,
            true,
            "___+++"
        )
        )
    }

    @Test
    fun deleteComment() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            0,
            1,
            1,
            true,
            "___"
        )
        )

        val result = CommentNoteService.delete(
            CommentNote(
                1,
                1,
                1,
                true,
                "___"
            )
        )

        assertTrue(result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun throwAlreadyDeletedComment() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            1,
            1,
            1,
            true,
            "___"
        )
        )

        CommentNoteService.delete(
            CommentNote(
                1,
                1,
                1,
                false,
                "___"
            )
        )

    }

    @Test(expected = CommentNotFoundException::class)
    fun throwVisibleDeleteComment() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            1,
            1,
            1,
            false,
            "___"
        )
        )

        CommentNoteService.delete(
            CommentNote(
                1,
                1,
                1,
                true,
                "___"
            )
        )

    }

    @Test(expected = CommentNotFoundException::class)
    fun throwIdDeleteComment() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            0,
            1,
            1,
            true,
            "___"
        )
        )

        CommentNoteService.delete(
            CommentNote(
                2,
                1,
                1,
                true,
                "___"
            )
        )

    }

    @Test
    fun restoreCommentTrue() {
        NoteService.add(
            Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            0,
            1,
            1,
            true,
            "___"
        )
        )

        CommentNoteService.delete(
            CommentNote(
            1,
            1,
            1,
            true,
            "___"
        )
        )

        val result = CommentNoteService.restore(1)

        assertTrue(result)
    }

    @Test
    fun restoreCommentFalse() {
        NoteService.add(Note(
            0,
            0,
            "_",
            true
        )
        )

        CommentNoteService.add(
            CommentNote(
            0,
            1,
            1,
            true,
            "___"
        )
        )

        CommentNoteService.delete(
            CommentNote(
            1,
            1,
            1,
            true,
            "___"
        )
        )

        val result = CommentNoteService.restore(2)

        assertFalse(result)
    }

    @Test
    fun getUserComment() {
        val ownerId = 12
        val comment = CommentNote(1, 1, ownerId, true, "____")
        val newComment = CommentNote(0, 1, ownerId, true, "____")
        val comments = arrayOf(comment)

        NoteService.add(Note(0, 12, "_", true))
        CommentNoteService.add(newComment)

        assertArrayEquals(CommentNoteService.getUser(ownerId),comments)
    }

    @Test(expected = CommentNotFoundException::class)
    fun throwIdGetUserComments() {
        val ownerId = 12
        val newComment = CommentNote(0, 1, 13, true, "____")

        NoteService.add(Note(0, 12, "_", true))
        CommentNoteService.add(newComment)
        CommentNoteService.getUser(ownerId)
    }

    @Test
    fun getByIdComment() {
        val id = 1
        val comment = CommentNote(id, 1, 0, true, "____")
        val newComment = CommentNote(0, 1, 0, true, "____")

        NoteService.add(Note(0, 12, "_", true))
        CommentNoteService.add(newComment)
        assertEquals(CommentNoteService.getById(id), comment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun throwGetByIdComment() {
        val id = 2
        val newComment = CommentNote(0, 1, 0, true, "____")

        NoteService.add(Note(0, 0, "_", true))
        CommentNoteService.add(newComment)
        CommentNoteService.getById(id)
    }
}