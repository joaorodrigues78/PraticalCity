package com.example.praticalcity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)


        val button_notas = findViewById<Button>(R.id.button_notas)

        //botão do login redireciona para a atividade das notas
        button_notas.setOnClickListener{
            val intent = Intent(this, Notas::class.java)
            startActivity(intent)
        }
    }
}