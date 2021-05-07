package com.example.praticalcity.api

data class Situacoes(
    val id: Int,
    val latitude: Float,
    val longitude: Float,
    val titulo: String,
    val descr: String,
    val foto: String
)