package com.example.praticalcity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praticalcity.adapters.NotasAdapter
import com.example.praticalcity.entities.notasEntities
import com.example.praticalcity.viewModel.NotasViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Notas : AppCompatActivity() {

    private lateinit var notasViewModel: NotasViewModel
    private val newNotasActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotasAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //ViewModel
        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        notasViewModel.allNotas.observe(this, Observer {
                notas -> notas?.let { adapter.setNotas(it) }
        })

        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, AddNota::class.java)
            //startActivity(intent)
            startActivityForResult(intent, newNotasActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // ADICIONAR NOTA
        if (requestCode == newNotasActivityRequestCode && resultCode == Activity.RESULT_OK){
            var titulo = data?.getStringExtra(AddNota.TITULO).toString()
            var observacao = data?.getStringExtra(AddNota.OBSERVACAO).toString()
            val nota = notasEntities(titulo = titulo, observacao = observacao)
                notasViewModel.insert(nota)
            Toast.makeText(this, R.string.notaGuardada, Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(
                applicationContext, R.string.notaNaoGuardada, Toast.LENGTH_LONG).show()
        }

    }

}