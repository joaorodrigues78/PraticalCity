package com.example.praticalcity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.praticalcity.R
import com.example.praticalcity.entities.notasEntities
//import com.example.praticalcity.notas
import java.util.concurrent.TimeoutException

class NotasAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<NotasAdapter.NotasViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notass = emptyList<notasEntities>()

    class NotasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val notasItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotasViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NotasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotasViewHolder, position: Int) {
        val current = notass[position]
        holder.notasItemView.text = current.nota
    }

    internal fun setNotas(notas: List<notasEntities>){
       this.notass = notas
       notifyDataSetChanged()
   }

    override fun getItemCount() = notass.size


}