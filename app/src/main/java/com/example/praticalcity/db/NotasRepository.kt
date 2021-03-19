package com.example.praticalcity.db

import androidx.lifecycle.LiveData
import com.example.praticalcity.dao.NotasDao
import com.example.praticalcity.entities.notasEntities

class NotasRepository (private val notasDao: NotasDao) {

    val allNotas: LiveData<List<notasEntities>> = notasDao.getNotasById()

    suspend fun insert(nota: notasEntities) {
            notasDao.insert(nota)
    }

}