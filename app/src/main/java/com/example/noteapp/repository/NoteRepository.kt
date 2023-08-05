package com.example.noteapp.repository

import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.model.Note

class NoteRepository(private val database: NoteDatabase) {
    suspend fun insertNote(note: Note) = database.noteDao().insertNote(note)
    suspend fun updateNote(note: Note) = database.noteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = database.noteDao().deleteNote(note)

    fun getAllNotes() = database.noteDao().getAllNotes()
    fun searchNote(query : String?) = database.noteDao().searchNote(query)
}