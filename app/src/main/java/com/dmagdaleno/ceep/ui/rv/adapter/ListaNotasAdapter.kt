package com.dmagdaleno.ceep.ui.rv.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmagdaleno.ceep.R
import com.dmagdaleno.ceep.model.Nota
import kotlinx.android.synthetic.main.item_nota.view.*

class ListaNotasAdapter(
        private val context: Context,
        private val notas: List<Nota>) : Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nota = notas[position]
        with(holder.itemView) {
            item_nota_titulo.text = nota.titulo
            item_nota_descricao.text = nota.descricao
        }
    }

    override fun getItemCount() = notas.size

    class NotaViewHolder(item: View): ViewHolder(item)
}
