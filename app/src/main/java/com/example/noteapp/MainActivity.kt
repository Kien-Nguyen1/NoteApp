package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.viewmodel.NoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMain)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))

        val viewModelProviderFactory = NoteViewModelProviderFactory(application, noteRepository)
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }
}