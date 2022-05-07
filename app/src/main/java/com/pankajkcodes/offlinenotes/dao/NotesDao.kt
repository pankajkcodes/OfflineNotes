package com.pankajkcodes.offlinenotes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pankajkcodes.offlinenotes.models.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    // below method is use to update the note.
    @Update
    suspend fun update(note: Note)

}