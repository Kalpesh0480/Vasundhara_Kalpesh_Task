package com.example.vasundhara_kalpesh_task.wallnotes

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vasundhara_kalpesh_task.databinding.ActivityWallNotesBinding
import com.example.vasundhara_kalpesh_task.wallnotes.adapter.NotesAdapter


class WallNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWallNotesBinding
    private lateinit var notesAdapter: NotesAdapter
    private val notesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_wall_notes)

        binding = ActivityWallNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()


    }
    private fun initView() {
        // editText.clearFocus()
        setOnclickLis()
        setAdapter()


    }

    private fun setAdapter() {

        notesAdapter = NotesAdapter(notesList) { position ->
            deleteNoteAt(position)
        }
        binding.recyclerView.adapter = notesAdapter

        val manager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager =manager //LinearLayoutManager(this)

    }

    private fun setOnclickLis() {

        binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        binding.addNoteButton.setOnClickListener {
            addNewNote()
        }


    }
    private fun addNewNote() {
        val editText = EditText(this)
        editText.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        editText.hint = "Enter your note"
        editText.setSingleLine()
        editText.setOnEditorActionListener { _, _, _ ->
            val noteText = editText.text.toString().trim()
            if (noteText.isNotEmpty()) {
                notesList.add(noteText)
                notesAdapter.notifyItemInserted(notesList.size - 1)
                true
            } else {
                false
            }
        }

        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("New Note")
            .setView(editText)
            .setPositiveButton("Add", null)
            .setNegativeButton("Cancel", null)
            .show()

        dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val noteText = editText.text.toString().trim()
            if (noteText.isNotEmpty()) {
                notesList.add(noteText)
                notesAdapter.notifyItemInserted(notesList.size - 1)
                dialog.dismiss()
            }
        }
    }
    private fun deleteNoteAt(position: Int) {
        notesList.removeAt(position)
        notesAdapter.notifyItemRemoved(position)
    }
}