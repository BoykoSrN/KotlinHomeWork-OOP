package ru.netology.service

import ru.netology.data.Note

object NoteService {
    private val notes = mutableListOf<Note>()
    private var noteId = 0

    fun add(note: Note): Long {
        noteId++
        val newNote = note.copy(id = note.id + noteId)
        notes.add(newNote)
        return newNote.id
    }

    fun update(note: Note): Boolean {
        for ((index, item) in notes.withIndex()) {
            if (item.id == note.id)
                notes[index] = note
            return true
        }
        return false
    }

//    fun delete(note: Note): Boolean {
//
//    }
}