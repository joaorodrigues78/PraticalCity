package com.example.praticalcity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

const val PARAM_ID: String = "id"
const val PARAM1_TITULO: String = "titulo"
const val PARAM2_OBSERVACAO: String = "observacao"

class Notas : AppCompatActivity(), CellClickListener {

    private lateinit var notasViewModel: NotasViewModel
    private val newNotasActivityRequestCode = 1
    private val newNotasActivityRequestCode1 = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotasAdapter(this, this)
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

    override fun onCellClickListener(data: notasEntities) {
        val intent = Intent(this@Notas, editNota::class.java)
        intent.putExtra(PARAM_ID, data.id.toString())
        intent.putExtra(PARAM1_TITULO, data.titulo.toString())
        intent.putExtra(PARAM2_OBSERVACAO, data.observacao.toString())
        startActivityForResult(intent, newNotasActivityRequestCode1)
        Log.e("***ID", data.id.toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Adicionar nova nota
        if (requestCode == newNotasActivityRequestCode && resultCode == Activity.RESULT_OK){
            var titulo = data?.getStringExtra(AddNota.TITULO).toString()
            var observacao = data?.getStringExtra(AddNota.OBSERVACAO).toString()
            val nota = notasEntities(titulo = titulo, observacao = observacao)
                notasViewModel.insert(nota)
            Toast.makeText(this, R.string.notaGuardada, Toast.LENGTH_SHORT).show()

        } else if (resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(
                applicationContext, R.string.notaNaoGuardada, Toast.LENGTH_LONG).show()
        }

        // Editar uma nota ou eliminar
        if (requestCode == newNotasActivityRequestCode1 && resultCode == Activity.RESULT_OK) {
            var edit_titulo = data?.getStringExtra(editNota.EDIT_TITULO).toString()
            var edit_observacao = data?.getStringExtra(editNota.EDIT_OBSERVACAO).toString()
            var id = data?.getStringExtra(editNota.EDIT_ID)
            var id_delete = data?.getStringExtra(editNota.DELETE_ID)
            if(data?.getStringExtra(editNota.STATUS) == "EDIT"){
                notasViewModel.update(id?.toIntOrNull(), edit_titulo, edit_observacao)
                Toast.makeText(this, R.string.notaEditada, Toast.LENGTH_SHORT).show()
            } else if(data?.getStringExtra(editNota.STATUS) == "DELETE"){
                notasViewModel.delete(id_delete?.toIntOrNull())
                Toast.makeText(this, R.string.notaEliminada, Toast.LENGTH_SHORT).show()
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            if(data?.getStringExtra(editNota.STATUS) == "EDIT"){
                Toast.makeText(this, R.string.erroEditar, Toast.LENGTH_SHORT).show()
            } else if(data?.getStringExtra(editNota.STATUS) == "DELETE"){
                Toast.makeText(this, R.string.erroEliminar, Toast.LENGTH_SHORT).show()
            }
        }

    }



}