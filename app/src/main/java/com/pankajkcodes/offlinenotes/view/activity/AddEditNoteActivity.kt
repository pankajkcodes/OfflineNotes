package com.pankajkcodes.offlinenotes.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.pankajkcodes.offlinenotes.databinding.ActivityAddEditNoteBinding
import com.pankajkcodes.offlinenotes.models.Note
import com.pankajkcodes.offlinenotes.viewmodel.NoteViewModal
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAddEditNoteBinding

    lateinit var viewModal: NoteViewModal
    var noteID = -1;
    lateinit var imgUri: Uri
    private var imgUrl :String = ""

    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()) {

        imgUrl = imgUri.toString()
        // Handle the returned Uri
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModal::class.java]


        // on below line we are getting data passed via an intent.
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            // on below line we are setting data to edit text.
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            binding.saveNoteBtn.text = "Update Note"
            binding.idEdtNoteName.setText(noteTitle)
            binding.idEdtNoteDesc.setText(noteDescription)
        } else {
            binding.saveNoteBtn.text = "Save Note"
        }



        imgUri = createImgUri()!!
        binding.selectImgBtn.setOnClickListener {

            getContent.launch(imgUri)
        }


        binding.saveNoteBtn.setOnClickListener {
            val noteTitle = binding.idEdtNoteName.text.toString()
            val noteDescription = binding.idEdtNoteDesc.text.toString()


            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(noteTitle, noteDescription,imgUrl, currentDateAndTime)
                    updatedNote.id = noteID
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    viewModal.addNote(Note(noteTitle, noteDescription, imgUrl,currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }

    private fun createImgUri(): Uri? {
        val timestamp = System.currentTimeMillis().toString()
        val image = File(applicationContext.filesDir, "$timestamp.png")

        return FileProvider.getUriForFile(applicationContext,
        "com.pankajkcodes.offlinenotes.fileProvider",
        image)
    }
}