package com.example.noteapp.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentUpdateNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class UpdateNoteFragment : Fragment() {
    private lateinit var binding: FragmentUpdateNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote : Note

    private val args : UpdateNoteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("EEE, d MM yyyy HH:mm a")
        val formattedTime = dateFormat.format(currentTime)

        binding.edtUpdateNoteTitle.setText(currentNote.title)
        binding.edtUpdateNoteDes.setText(currentNote.description)

        binding.fabDone.setOnClickListener {
            val title = binding.edtUpdateNoteTitle.text.toString().trim()
            val description = binding.edtUpdateNoteDes.text.toString().trim()

            if (title.isNotEmpty())
            {
                val note = Note(currentNote.id, title, description, "Cap nhat lan cuoi $formattedTime")
                noteViewModel.updateNote(note)
                Toast.makeText(activity, "Note update successfully!", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            else
            {
                Toast.makeText(activity, "Please enter note title!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_icon -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Warning!")
            setMessage("Ban co chac chan muon xoa?")
            setPositiveButton("Yes"){_, _ ->
                noteViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("No", null)
        }.create().show()
    }
}