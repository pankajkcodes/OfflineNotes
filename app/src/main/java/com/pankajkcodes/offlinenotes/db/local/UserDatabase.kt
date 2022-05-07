package com.pankajkcodes.offlinenotes.db.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.pankajkcodes.offlinenotes.dao.UserDao
import com.pankajkcodes.offlinenotes.db.local.UserDatabase
import androidx.room.Room
import com.pankajkcodes.offlinenotes.models.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao?

    companion object {
        var database: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase? {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java, "userDatabase"
                )
                    .allowMainThreadQueries().build()
            }
            return database
        }
    }
}