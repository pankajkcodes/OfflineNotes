package com.pankajkcodes.offlinenotes.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pankajkcodes.offlinenotes.view.activity.AddEditNoteActivity
import com.pankajkcodes.offlinenotes.R
import com.pankajkcodes.offlinenotes.models.Note
import com.pankajkcodes.offlinenotes.view.activity.ShowNotesActivity

class NoteAdapter(context: Context,  val noteClickDeleteInterface: NoteClickDeleteInterface,) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val notes = allNotes[position]

        // on below line we are setting data to item of recycler view.
        holder.noteTV.text = allNotes.get(position).noteTitle
        holder.dateTV.text = "Last Updated : " + allNotes.get(position).timeStamp

        holder.deleteIV.setOnClickListener {
            // on below line we are calling a note click
            // interface and we are passing a position to it.
              noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        // on below line we are adding click listener
        // to our recycler view item.
        holder.itemView.setOnClickListener {
            // on below line we are calling a note click interface
            // and we are passing a position to it.
            val intent = Intent(it.context, ShowNotesActivity::class.java)
            intent.putExtra("noteTitle", notes.noteTitle)
            intent.putExtra("noteDescription", notes.noteDescription)
            intent.putExtra("noteImg", notes.noteImg)
            intent.putExtra("noteId", notes.id)
            it.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener{

            val intent = Intent(it.context, AddEditNoteActivity::class.java)
            intent.putExtra("noteType", "Edit")
            intent.putExtra("noteTitle", notes.noteTitle)
            intent.putExtra("noteDescription", notes.noteDescription)
            intent.putExtra("noteImg", notes.noteImg)
            intent.putExtra("noteId", notes.id)
            it.context.startActivity(intent)

            true
        }
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our list size.
        return allNotes.size
    }

    // below method is use to update our list of notes.
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
    interface NoteClickDeleteInterface {
        // creating a method for click
        // action on delete image view.
        fun onDeleteIconClick(note: Note)
    }

}

