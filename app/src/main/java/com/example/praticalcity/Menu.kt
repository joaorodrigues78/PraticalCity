package com.example.praticalcity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.praticalcity.api.Situacao

class Menu : AppCompatActivity() {

    private lateinit var shared_preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        shared_preferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)

    }
    /*
    fun situacoes(view: View){
        val intent = Intent(this@Menu, situacoes::class.java)
        startActivity(intent)
    }
    */

    fun situacoes_mapa(view: View){
        val intent = Intent(this@Menu, MapsActivity::class.java)
        startActivity(intent)
    }

    /*fun situacoes_reportar(view: View){
        val intent = Intent(this@Menu, Situacao_reportar::class.java)
        startActivity(intent)
    }*/

    fun logout(view: View){
        val shared_preferences_edit : SharedPreferences.Editor = shared_preferences.edit()
        shared_preferences_edit.clear()
        shared_preferences_edit.apply()

        val intent = Intent(this@Menu, Login::class.java)
        startActivity(intent)
        finish()
    }
}