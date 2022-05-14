package ru.netology.service

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import ru.netology.data.CommentNote
import ru.netology.data.Note

class NoteServiceTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        NoteService.clearNotes()
    }

    @Test
    fun addNote() {
        val expectedId = 1
        val note = Note(
            0,
            0,
            "_",
            true
        )

        val actualId = NoteService.addNote(note)

        assertEquals(expectedId, actualId)
    }

    @Test
    fun updateNote() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        val result = NoteService.updateNote(Note(
            1,
            0,
            "+",
            true
        ))

        assertTrue(result)
    }

    @Test(expected = NoteService.NoteNotFoundException::class)
    fun throwIdUpdateNote() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.updateNote(Note(
            2,
            0,
            "+",
            true
        ))
    }

    @Test(expected = NoteService.NoteNotFoundException::class)
    fun throwVisibleUpdateNote() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            false
        ))

        NoteService.updateNote(Note(
            1,
            0,
            "+",
            true
        ))
    }

    @Test
    fun deleteNote() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        val result = NoteService.deleteNote(Note(
            1,
            0,
            "_",
            true
        ))

        assertTrue(result)
    }


    @Test(expected = NoteService.NoteNotFoundException::class)
    fun throwVisibleDeleteNote() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        NoteService.deleteNote(Note(
            1,
            0,
            "+",
            false
        ))
    }

    @Test(expected = NoteService.NoteNotFoundException::class)
    fun throwIdDeleteNote() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        NoteService.deleteNote(Note(
            2,
            0,
            "+",
            true
        ))
    }

    @Test
    fun getUserNotes() {
        val ownerId = 12
        val note = Note(1,ownerId,"___", true)
        val newNote = Note(0,ownerId,"___", true)
        val notes = arrayOf(note)

        NoteService.addNote(newNote)

        assertArrayEquals(NoteService.getUserNotes(ownerId),notes)
    }

    @Test(expected = NoteService.NoteNotFoundException::class)
    fun throwIdGetUserNotes() {
        val ownerId = 12
        val newNote = Note(0,13,"___", true)

        NoteService.addNote(newNote)
        NoteService.getUserNotes(ownerId)
    }

    @Test
    fun getById() {
        val id = 1
        val note = Note(id,1,"___", true)
        val newNote = Note(0,1,"___", true)
        NoteService.addNote(newNote)
        assertEquals(NoteService.getById(id), note)
    }

    @Test(expected = NoteService.NoteNotFoundException::class)
    fun throwGetById() {
        val id = 1
        val newNote = Note(1,1,"___", true)
        NoteService.addNote(newNote)
        NoteService.getById(id)
    }

    @Test
    fun addComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        val result = NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        assertTrue(result)
    }

    @Test(expected = NoteService.NoteNotFoundException::class)
    fun throwIdAddComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            2,
            1,
            true,
            "___"
        ))
    }

    @Test(expected = NoteService.NoteNotFoundException::class)
    fun throwVisibleAddComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            false
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))
    }


    @Test
    fun updateComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        val result = NoteService.updateComment(
            CommentNote(
                1,
                1,
                1,
                true,
                "___+++"
        ))

        assertTrue(result)
    }

    @Test(expected = NoteService.CommentNotFoundException::class)
    fun throwIdUpdateComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        NoteService.updateComment(CommentNote(
            2,
            1,
            1,
            true,
            "___+++"
        ))
    }

    @Test(expected = NoteService.CommentNotFoundException::class)
    fun throwVisibleUpdateComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            false,
            "___"
        ))

        NoteService.updateComment(CommentNote(
            2,
            1,
            1,
            true,
            "___+++"
        ))
    }

    @Test
    fun deleteComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        val result = NoteService.deleteComment(
            CommentNote(
                1,
                1,
                1,
                true,
                "___"
        ))

        assertTrue(result)
    }

    @Test(expected = NoteService.CommentNotFoundException::class)
    fun throwAlreadyDeletedComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        NoteService.deleteComment(
            CommentNote(
                1,
                1,
                1,
                false,
                "___"
            ))

    }

    @Test(expected = NoteService.CommentNotFoundException::class)
    fun throwVisibleDeleteComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            false,
            "___"
        ))

        NoteService.deleteComment(
            CommentNote(
                1,
                1,
                1,
                true,
                "___"
            ))

    }

    @Test(expected = NoteService.CommentNotFoundException::class)
    fun throwIdDeleteComment() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        NoteService.deleteComment(
            CommentNote(
                2,
                1,
                1,
                true,
                "___"
            ))

    }

    @Test
    fun restoreCommentTrue() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        NoteService.deleteComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        val result = NoteService.restoreComment(CommentNote(
            1,
            1,
            1,
            false,
            "___"
        ))

        assertTrue(result)
    }

    @Test
    fun restoreCommentFalse() {
        NoteService.addNote(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.addComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        NoteService.deleteComment(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        val result = NoteService.restoreComment(CommentNote(
            2,
            1,
            1,
            false,
            "___"
        ))

        assertFalse(result)
    }

}