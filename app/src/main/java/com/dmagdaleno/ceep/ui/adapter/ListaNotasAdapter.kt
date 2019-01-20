package com.dmagdaleno.ceep.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import com.dmagdaleno.ceep.R
import com.dmagdaleno.ceep.model.Nota

import kotlinx.android.synthetic.main.item_nota.view.item_nota_titulo
import kotlinx.android.synthetic.main.item_nota.view.item_nota_descricao

class ListaNotasAdapter(
        val context: Context,
        val notas: List<Nota>
): BaseAdapter() {

    override fun getView(posicao: Int, view: View?, viewGroup: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_nota, viewGroup, false)

        val nota = notas[posicao]

        with(viewCriada) {
            item_nota_titulo.text = nota.titulo
            item_nota_descricao.text = nota.descricao
        }

        return viewCriada
    }

    override fun getItem(posicao: Int) = notas[posicao]

    override fun getItemId(posicao: Int) = posicao.toLong()

    override fun getCount() = notas.size
}