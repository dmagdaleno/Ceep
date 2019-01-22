package com.dmagdaleno.ceep.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.dmagdaleno.ceep.R
import com.dmagdaleno.ceep.dao.NotaDAO
import com.dmagdaleno.ceep.model.Nota
import com.dmagdaleno.ceep.ui.rv.adapter.ListaNotasAdapter
import kotlinx.android.synthetic.main.activity_lista_notas.*

class ListaNotasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        val dao = NotaDAO()

        criaNotasDeExemplo(dao)

        configuraRecyclerView(dao)
    }

    private fun configuraRecyclerView(dao: NotaDAO) {
        lista_notas.layoutManager = LinearLayoutManager(this)
        lista_notas.adapter = ListaNotasAdapter(this, dao.todas())
    }

    private fun criaNotasDeExemplo(dao: NotaDAO) {
        val nota1 = Nota("Primeira Nota", "Descrição da nota")
        val nota2 = Nota("Segunda Nota", "Descrição beeeeee maior que a descrição da primeira nota")
        dao.insere(nota1, nota2)
    }
}
