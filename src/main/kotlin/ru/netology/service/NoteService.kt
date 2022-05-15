package ru.netology.service

import ru.netology.data.CommentNote
import ru.netology.data.Note

class NoteNotFoundException(message: String): RuntimeException(message)
class CommentNotFoundException(message: String): RuntimeException(message)

private val notes = mutableListOf<Note>()
private val commentsNotes = mutableListOf<CommentNote>()
private var noteId = 0
private var commentId = 0

object NoteService: BaseService<Note> {

    override fun add(entity: Note): Int {
        noteId++
        val newNote = entity.copy(id = entity.id + noteId)
        notes.add(newNote)
        return newNote.id
    }

    override fun update(entity: Note): Boolean {
        for ((index, item) in notes.withIndex()) {
            if ((item.id == entity.id) && item.visible) {
                notes[index] = entity
                return true
            }
        }
        throw NoteNotFoundException("No note with ID ${entity.id} or it has been deleted")
    }

    override fun delete(entity: Note): Boolean {
        if (!entity.visible)
            throw NoteNotFoundException("Note with ID ${entity.id} was deleted")
        for ((index, item) in notes.withIndex()) {
            if (notes[index].id == entity.id) {
                notes[index] = item.copy(visible = false)
                for ((i, it) in commentsNotes.withIndex()) {
                    if (commentsNotes[i].noteId == entity.id) {
                        commentsNotes[i] = it.copy(visible = false)
                    }
                }
                return true
            }
        }
        throw NoteNotFoundException("No note with ID ${entity.id}")
    }

    override fun getUser(ownerId: Int): Array<Note> {
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

    override fun getById(id: Int): Note {
        for ((index, item) in notes.withIndex()) {
            if (item.id == id) {
                if (notes[index].visible) {
                    return notes[index]
                }
            }
        }
        throw NoteNotFoundException("No note with ID $id or it has been deleted")
    }

    override fun restore(id: Int): Boolean {
        for ((index, item) in notes.withIndex()) {
            if ((item.id == id) && !item.visible) {
                notes[index] = item.copy(visible = true)
                for ((i, it) in commentsNotes.withIndex()) {
                    if (commentsNotes[i].noteId == id) {
                        commentsNotes[i] = it.copy(visible = true)
                    }
                }
                return true
            }
        }
        return false
    }

    fun clearNotes() {
        commentId = 0
        noteId = 0
        notes.clear()
        commentsNotes.clear()
    }
}

object CommentNoteService: BaseService<CommentNote> {

    override fun add(entity: CommentNote): Int {
        for (note in notes) {
            if ((note.id == entity.noteId) && note.visible) {
                commentId++
                val newComment = entity.copy(id = entity.id + commentId)
                commentsNotes.add(newComment)
                return newComment.id
            }
        }
        throw NoteNotFoundException("No note with ID ${entity.noteId} or it has been deleted")
    }

    override fun update(entity: CommentNote): Boolean {
        for ((index, item) in commentsNotes.withIndex()) {
            if ((item.id == entity.id) && item.visible) {
                commentsNotes[index] = entity
                return true
            }
        }
        throw CommentNotFoundException("Comment with ID ${entity.id} was deleted")
    }

    override fun delete(entity: CommentNote): Boolean {
        if (!entity.visible)
            throw CommentNotFoundException("Comment with ID ${entity.id} was deleted")
        for ((index, item) in commentsNotes.withIndex()) {
            if ((commentsNotes[index].id == entity.id) && item.visible) {
                commentsNotes[index] = item.copy(visible = false)
                return true
            }
        }
        throw CommentNotFoundException("Comment with ID ${entity.id} was deleted")
    }

    override fun getUser(ownerId: Int): Array<CommentNote> {
        var userCommentNotes = emptyArray<CommentNote>()
        for ((index, item) in commentsNotes.withIndex()) {
            if (item.ownerId == ownerId) {
                if (commentsNotes[index].visible) {
                    userCommentNotes += commentsNotes[index]
                    return userCommentNotes
                }
            }
        }
        throw CommentNotFoundException("No comment with ownerID $ownerId or it has been deleted")
    }

    override fun getById(id: Int): CommentNote {
        for ((index, item) in commentsNotes.withIndex()) {
            if (item.id == id) {
                if (commentsNotes[index].visible) {
                    return commentsNotes[index]
                }
            }
        }
        throw CommentNotFoundException("No comment with ID $id or it has been deleted")
    }

    override fun restore(id: Int): Boolean {
        for ((index, item) in commentsNotes.withIndex()) {
            if ((item.id == id) && !item.visible) {
                commentsNotes[index] = item.copy(visible = true)
                return true
            }
        }
        return false
    }

    fun clearComments() {
        noteId = 0
        commentId = 0
        notes.clear()
        commentsNotes.clear()
    }
}