package com.pankajkcodes.offlinenotes.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pankajkcodes.offlinenotes.R
import com.pankajkcodes.offlinenotes.adapter.NoteAdapter
import com.pankajkcodes.offlinenotes.models.Note
import com.pankajkcodes.offlinenotes.viewmodel.NoteViewModal

class MainActivity : AppCompatActivity() , NoteAdapter.NoteClickDeleteInterface {

    lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)


        notesRV.layoutManager = LinearLayoutManager(this)
        val noteRVAdapter = NoteAdapter(this,this)

        notesRV.adapter = noteRVAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }


    override fun onDeleteIconClick(note: Note) {

        viewModal.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}