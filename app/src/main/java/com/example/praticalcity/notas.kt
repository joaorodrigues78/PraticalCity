package com.example.praticalcity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praticalcity.adapters.NotasAdapter
import com.example.praticalcity.viewModel.NotasViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class notas : AppCompatActivity() {

    private lateinit var notasViewModel: NotasViewModel
    private val newNotaActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotasAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //ViewModel
        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        notasViewModel.allNotas.observe(this, {
                notas -> notas?.let{adapter.setNotas(it)}
        })

        //Fab
        /*
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(
            val intent = Intent(this@notas, AddNota::class.java)
            startActivityForResult(intent, newNotaActivityRequestCode)
        )
        */

    }
}