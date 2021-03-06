package com.example.praticalcity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praticalcity.adapters.SituacoesAdapter
import com.example.praticalcity.api.EndPoints
import com.example.praticalcity.api.ServiceBuilder
import com.example.praticalcity.api.Situacao
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class situacoes : AppCompatActivity() {

    private lateinit var shared_preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_situacoes)
        shared_preferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getSituacoes()
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview_problema)

        call.enqueue(object : Callback<List<Situacao>> {
            override fun onResponse(call: Call<List<Situacao>>, response: Response<List<Situacao>>){
                if (response.isSuccessful){
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@situacoes)
                        adapter = SituacoesAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<Situacao>>, t: Throwable){
                Toast.makeText(this@situacoes,"${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getSingle(view: View){
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getSituacaoById(1)

        call.enqueue(object : Callback<Situacao>{
            override fun onResponse(call: Call<Situacao>, response: Response<Situacao>){
                if (response.isSuccessful){
                    val c: Situacao = response.body()!!
                    Toast.makeText(this@situacoes,"Situacao obtida", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Situacao>, t: Throwable){
                Toast.makeText(this@situacoes,"${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun logout(view: View){
        val shared_preferences_edit : SharedPreferences.Editor = shared_preferences.edit()
        shared_preferences_edit.clear()
        shared_preferences_edit.apply()

        val intent = Intent(this@situacoes, Login::class.java)
        startActivity(intent)
        finish()
    }


}

