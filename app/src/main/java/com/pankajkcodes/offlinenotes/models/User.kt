package com.pankajkcodes.offlinenotes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String? = null
    var email: String? = null
    var mobile: String? = null
    var pass: String? = null
}