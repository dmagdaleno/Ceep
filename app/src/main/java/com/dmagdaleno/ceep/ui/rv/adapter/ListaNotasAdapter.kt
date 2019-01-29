package com.dmagdaleno.ceep.ui.rv.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmagdaleno.ceep.R
import com.dmagdaleno.ceep.model.Nota
import kotlinx.android.synthetic.main.item_nota.view.*

class ListaNotasAdapter(
        private val context: Context,
        private val notas: MutableList<Nota>) : Adapter<ListaNotasAdapter.NotaViewHolder>() {

    var onItemClick: (posicao: Int, nota: Nota) -> Unit = { _, _ ->
        Log.w(TAG, "onItemClick não foi implementado")
    }

    companion object {
        const val TAG = "ListaNotasAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.vincula(nota)
    }

    override fun getItemCount() = notas.size

    inner class NotaViewHolder(item: View): ViewHolder(item) {
        private val titulo = item.item_nota_titulo
        private val descricao = item.item_nota_descricao
        private lateinit var nota: Nota

        init {
            item.setOnClickListener {
                onItemClick(adapterPosition, nota)
            }
        }

        fun vincula(nota: Nota) {
            this.nota = nota
            preencheCampos(nota)
        }

        private fun preencheCampos(nota: Nota) {
            titulo.text = nota.titulo
            descricao.text = nota.descricao
        }
    }

    fun adiciona(nota: Nota){
        this.notas.add(nota)
        this.notifyDataSetChanged()
    }

    fun altera(posicao: Int, nota: Nota) {
        this.notas[posicao] = nota
        this.notifyDataSetChanged()
    }

    fun remove(posicao: Int) {
        this.notas.removeAt(posicao)
        notifyDataSetChanged()
    }
}
