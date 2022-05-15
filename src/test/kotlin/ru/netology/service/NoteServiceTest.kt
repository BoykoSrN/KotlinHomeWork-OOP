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

        val actualId = NoteService.add(note)

        assertEquals(expectedId, actualId)
    }

    @Test
    fun updateNote() {
        NoteService.add(Note(
            0,
            0,
            "_",
            true
        ))

        val result = NoteService.update(Note(
            1,
            0,
            "+",
            true
        ))

        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun throwIdUpdateNote() {
        NoteService.add(Note(
            0,
            0,
            "_",
            true
        ))

        NoteService.update(Note(
            2,
            0,
            "+",
            true
        ))
    }

    @Test(expected = NoteNotFoundException::class)
    fun throwVisibleUpdateNote() {
        NoteService.add(Note(
            0,
            0,
            "_",
            false
        ))

        NoteService.update(Note(
            1,
            0,
            "+",
            true
        ))
    }

    @Test
    fun deleteNote() {
        NoteService.add(Note(
            0,
            0,
            "_",
            true
        ))

        CommentNoteService.add(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        val result = NoteService.delete(Note(
            1,
            0,
            "_",
            true
        ))

        assertTrue(result)
    }


    @Test(expected = NoteNotFoundException::class)
    fun throwVisibleDeleteNote() {
        NoteService.add(Note(
            0,
            0,
            "_",
            true
        ))

        CommentNoteService.add(CommentNote(
            0,
            1,
            1,
            true,
            "___"
        ))

        NoteService.delete(Note(
            1,
            0,
            "+",
            false
        ))
    }

    @Test(expected = NoteNotFoundException::class)
    fun throwIdDeleteNote() {
        NoteService.add(Note(
            0,
            0,
            "_",
            true
        ))

        CommentNoteService.add(CommentNote(
            1,
            1,
            1,
            true,
            "___"
        ))

        NoteService.delete(Note(
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

        NoteService.add(newNote)

        assertArrayEquals(NoteService.getUser(ownerId),notes)
    }

    @Test(expected = NoteNotFoundException::class)
    fun throwIdGetUserNotes() {
        val ownerId = 12
        val newNote = Note(0,13,"___", true)

        NoteService.add(newNote)
        NoteService.getUser(ownerId)
    }

    @Test
    fun getById() {
        val id = 1
        val note = Note(id,1,"___", true)
        val newNote = Note(0,1,"___", true)
        NoteService.add(newNote)
        assertEquals(NoteService.getById(id), note)
    }

    @Test(expected = NoteNotFoundException::class)
    fun throwGetById() {
        val id = 1
        val newNote = Note(1,1,"___", true)
        NoteService.add(newNote)
        NoteService.getById(id)
    }

    @Test
    fun restoreNoteTrue() {
        NoteService.add(Note(
            0,
            0,
            "_",
            true
        ))

        CommentNoteService.add(CommentNote(
            0,
            1,
            1,
            true,
            "___"
        ))

        NoteService.delete(Note(
            1,
            0,
            "+",
            true
        ))

        val result = NoteService.restore(1)
        assertTrue(result)
    }

    @Test
    fun restoreNoteFalse() {
        NoteService.add(Note(
            0,
            0,
            "_",
            true
        ))

        CommentNoteService.add(CommentNote(
            0,
            1,
            1,
            true,
            "___"
        ))

        NoteService.delete(Note(
            1,
            0,
            "+",
            true
        ))

        val result = NoteService.restore(2)
        assertFalse(result)
    }


}