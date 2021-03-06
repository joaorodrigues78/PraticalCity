package com.example.praticalcity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.praticalcity.R
import com.example.praticalcity.api.Situacao

class SituacoesAdapter (val situacao: List<Situacao>) : RecyclerView.Adapter<SituacaoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SituacaoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_itemsituacoes,parent, false)
        return SituacaoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SituacaoViewHolder, position: Int) {
        return holder.bind(situacao[position])
    }

    override fun getItemCount(): Int {
        return situacao.size
    }

}

class SituacaoViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    val latitude: TextView = itemView.findViewById(R.id.latitude)
    val longitude: TextView = itemView.findViewById(R.id.longitude)
    val titulo: TextView = itemView.findViewById(R.id.titulo)
    val descr: TextView = itemView.findViewById(R.id.descr)

    fun bind(situacao: Situacao){
        latitude.text = situacao.latitude.toString()
        longitude.text = situacao.longitude.toString()
        titulo.text = situacao.titulo
        descr.text = situacao.descr
    }

}