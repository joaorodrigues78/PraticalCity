package com.example.praticalcity.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas_table")

class notasEntities (

    @PrimaryKey(autoGenerate = true) val id: Int? = null,

    @ColumnInfo(name = "titulo") val titulo: String,

    @ColumnInfo(name = "observacao") val observacao: String

)