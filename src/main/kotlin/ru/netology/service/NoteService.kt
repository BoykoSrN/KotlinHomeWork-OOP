package ru.netology.service

import ru.netology.data.CommentNote
import ru.netology.data.Note

object NoteService {
    private val notes = mutableListOf<Note>()
    private val commentsNotes = mutableListOf<CommentNote>()
    private var noteId = 0

    fun addNote(note: Note): Int {
        noteId++
        val newNote = note.copy(id = note.id + noteId)
        notes.add(newNote)
        return newNote.id
    }

    fun updateNote(note: Note): Boolean {
        for ((index, item) in notes.withIndex()) {
            if ((item.id == note.id) && item.visible) {
                notes[index] = note
                return true
            }
        }
        throw NoteNotFoundException("No note with ID ${note.id} or it has been deleted")
    }

    fun deleteNote(note: Note): Boolean {
        if (!note.visible)
            throw NoteNotFoundException("Note with ID ${note.id} was deleted")
        for ((index, item) in notes.withIndex()) {
            if (notes[index].id == note.id) {
                notes[index] = item.copy(visible = false)
                for ((i, it) in commentsNotes.withIndex()) {
                    if (commentsNotes[i].noteId == note.id) {
                        commentsNotes[i] = it.copy(visible = false)
                    }
                }
                return true
            }
        }
        throw NoteNotFoundException("No note with ID ${note.id}")
    }

    fun getUserNotes(ownerId: Int): Array<Note> {
        var userNotes = emptyArray<Note>()
        for ((index, item) in notes.withIndex()) {
            if (item.ownerId == ownerId) {
                if (notes[index].visible) {
                    userNotes += notes[index]
                    return userNotes
                }
            }
        }
        throw NoteNotFoundException("No note with ownerID $ownerId or it has been deleted")
    }

    fun getById(id: Int): Note {
        for ((index, item) in notes.withIndex()) {
            if (item.id == id) {
                if (notes[index].visible) {
                    return notes[index]
                }
            }
        }
        throw NoteNotFoundException("No note with ID $id or it has been deleted")
    }

    fun addComment(comment: CommentNote) : Boolean {
        for (note in notes) {
            if ((note.id == comment.noteId) && note.visible) {
               commentsNotes.add(comment)
                return true
            }
        }
        throw NoteNotFoundException("No note with ID ${comment.noteId} or it has been deleted")
    }

    fun updateComment(comment: CommentNote): Boolean {
        for ((index, item) in commentsNotes.withIndex()) {
            if ((item.id == comment.id) && item.visible) {
                commentsNotes[index] = comment
                return true
            }
        }
        throw CommentNotFoundException("Comment with ID ${comment.id} was deleted")
    }

    fun deleteComment(comment: CommentNote): Boolean {
        if (!comment.visible)
            throw CommentNotFoundException("Comment with ID ${comment.id} was deleted")
        for ((index, item) in commentsNotes.withIndex()) {
            if ((commentsNotes[index].id == comment.id) && item.visible) {
                commentsNotes[index] = item.copy(visible = false)
                return true
            }
        }
        throw CommentNotFoundException("Comment with ID ${comment.id} was deleted")
    }

    fun restoreComment(comment: CommentNote): Boolean {
        for ((index, item) in commentsNotes.withIndex()) {
            if ((item.id == comment.id) && !item.visible) {
                commentsNotes[index] = item.copy(visible = true)
                return true
            }
        }
        return false
    }

    fun clearNotes() {
        noteId = 0
        notes.clear()
        commentsNotes.clear()
    }

    class NoteNotFoundException(message: String): RuntimeException(message)
    class CommentNotFoundException(message: String): RuntimeException(message)
}
