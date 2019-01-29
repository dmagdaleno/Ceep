package com.dmagdaleno.ceep.ui.rv.helper.callback

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.dmagdaleno.ceep.ui.rv.adapter.ListaNotasAdapter

class NotaItemTouchHelperCallback(
        private val adapter: ListaNotasAdapter): ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val marcadoresDeslize = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val marcadoresArrastar = ItemTouchHelper.DOWN or ItemTouchHelper.UP or marcadoresDeslize
        return makeMovementFlags(marcadoresArrastar, marcadoresDeslize)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val posicaoInicial = viewHolder.adapterPosition
        val posicaoFinal = target.adapterPosition
        adapter.troca(posicaoInicial, posicaoFinal)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val posicao = viewHolder.adapterPosition
        adapter.remove(posicao)
    }
}