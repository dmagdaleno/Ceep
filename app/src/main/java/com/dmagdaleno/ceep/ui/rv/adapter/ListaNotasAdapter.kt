package com.dmagdaleno.ceep.ui.rv.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dmagdaleno.ceep.R
import com.dmagdaleno.ceep.model.Nota
import kotlinx.android.synthetic.main.item_nota.view.*

class ListaNotasAdapter(
        private val context: Context,
        private val notas: List<Nota>) : Adapter<ListaNotasAdapter.NotaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.vincula(nota)
    }

    override fun getItemCount() = notas.size

    class NotaViewHolder(item: View): ViewHolder(item) {
        private val titulo = item.item_nota_titulo
        private val descricao = item.item_nota_descricao

        fun vincula(nota: Nota) {
            titulo.text = nota.titulo
            descricao.text = nota.descricao
        }
    }
}
