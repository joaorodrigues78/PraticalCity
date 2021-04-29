package com.example.praticalcity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val button_notas = findViewById<Button>(R.id.button_notas)
        val button_login = findViewById<Button>(R.id.button_login)

        //botão das notas redireciona para a atividade das notas
        button_notas.setOnClickListener{
            val intent = Intent(this, Notas::class.java)
            startActivity(intent)
        }

        //botão do login redireciona para o login
        button_login.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }


}