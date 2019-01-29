package com.dmagdaleno.ceep.ui.rv.helper.callback

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.dmagdaleno.ceep.dao.NotaDAO
import com.dmagdaleno.ceep.ui.rv.adapter.ListaNotasAdapter

class NotaItemTouchHelperCallback(
        private val adapter: ListaNotasAdapter): ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val marcadores = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0, marcadores)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val posicao = viewHolder.adapterPosition
        //NotaDAO().remove(posicao)
        adapter.remove(posicao)
    }
}