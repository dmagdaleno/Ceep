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

        for(i in 1 .. 10000){
            val nota = Nota("Nota $i", "Descrição da nota $i")
            dao.insere(nota)
        }

        lista_notas.layoutManager = LinearLayoutManager(this)
        lista_notas.adapter = ListaNotasAdapter(this, dao.todas())
    }
}
