package com.pankajkcodes.offlinenotes.db.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pankajkcodes.offlinenotes.dao.NotesDao
import com.pankajkcodes.offlinenotes.models.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "notesDatabase"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}