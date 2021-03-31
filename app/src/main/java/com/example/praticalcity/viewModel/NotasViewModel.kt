package com.example.praticalcity.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.praticalcity.db.NotasRepository
import com.example.praticalcity.db.NotasDB
import com.example.praticalcity.entities.notasEntities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotasViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NotasRepository

    val allNotas: LiveData<List<notasEntities>>

    init {
        val notasDaoo = NotasDB.getDatabase(application, viewModelScope).notasDao()
        repository = NotasRepository(notasDaoo)
        allNotas = repository.allNotas
    }

    fun insert(nota: notasEntities) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(nota)
    }

    fun update(id: Int?, titulo: String, observacao: String) = viewModelScope.launch{
        repository.update(id, titulo, observacao)
    }

    fun delete(id: Int?) = viewModelScope.launch{
        repository.delete(id)
    }

}