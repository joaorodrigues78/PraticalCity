package com.example.praticalcity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button_notas = findViewById<Button>(R.id.button_notas)

        //botão do login redireciona para a atividade das notas
        button_notas.setOnClickListener{
            val intent = Intent(this, notas::class.java)
            startActivity(intent)
        }
    }
}