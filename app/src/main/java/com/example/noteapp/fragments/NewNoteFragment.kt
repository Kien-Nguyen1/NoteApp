package com.example.noteapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNewNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {
    private lateinit var binding: FragmentNewNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        mView = view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_icon -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveNote(view: View) {
        val title = binding.edtNewNoteTitle.text.toString().trim()
        val description = binding.edtNewNoteDes.text.toString().trim()

        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("EEE, d MM yyyy HH:mm a")
        val formattedTime = dateFormat.format(currentTime)

        if (title.isNotEmpty())
        {
            val note = Note(0, title, description, "Cap nhat lan cuoi $formattedTime")
            noteViewModel.insertNote(note)
            Toast.makeText(activity, "Save note successfully!", Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        }
        else
        {
            Toast.makeText(activity, "Please enter note title", Toast.LENGTH_SHORT).show()
        }
    }
}