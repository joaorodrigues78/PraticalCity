package com.example.praticalcity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.praticalcity.entities.notasEntities


@Dao
interface NotasDao {

    @Query("SELECT * FROM notas_table ORDER BY id DESC")
    fun getNotasById(): LiveData<List<notasEntities>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: notasEntities)

    @Query("UPDATE notas_table SET titulo = :titulo, observacao = :observacao WHERE id = :id")
    suspend fun update(id: Int?, titulo: String, observacao: String)

    @Query("DELETE FROM notas_table")
    suspend fun deleteAll()

    @Query("DELETE FROM notas_table WHERE id = :id")
    suspend fun delete(id: Int?)
}