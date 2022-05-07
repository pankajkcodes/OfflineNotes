package com.pankajkcodes.offlinenotes.repository

import androidx.lifecycle.LiveData
import com.pankajkcodes.offlinenotes.dao.NotesDao
import com.pankajkcodes.offlinenotes.models.Note

class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()


    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }
}