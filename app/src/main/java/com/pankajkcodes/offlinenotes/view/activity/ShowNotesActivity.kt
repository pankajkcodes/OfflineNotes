package com.pankajkcodes.offlinenotes.view.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pankajkcodes.offlinenotes.databinding.ActivityShowNotesBinding


class ShowNotesActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowNotesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val title = intent.getStringExtra("noteTitle")
        val description = intent.getStringExtra("noteDescription")
        val image = intent.getStringExtra("noteImg")


        binding.dNoteTitle.text = title
        binding.dNoteDescription.text = description
        binding.dNoteImg.setImageURI((Uri.parse(image)))

    }
}