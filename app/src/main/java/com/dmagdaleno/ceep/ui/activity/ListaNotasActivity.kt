package com.dmagdaleno.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dmagdaleno.ceep.dao.NotaDAO
import com.dmagdaleno.ceep.model.Nota
import com.dmagdaleno.ceep.R
import com.dmagdaleno.ceep.ui.rv.adapter.ListaNotasAdapter
import kotlinx.android.synthetic.main.activity_lista_notas.*

class ListaNotasActivity : AppCompatActivity() {

    private lateinit var adapter: ListaNotasAdapter

    private lateinit var notas: MutableList<Nota>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        val dao = NotaDAO()

        notas = criaNotasDeExemplo(dao)

        configuraRecyclerView()

        lista_notas_insere_nota.setOnClickListener {
            val i = Intent(this, FormularioNotaActivity::class.java)
            startActivityForResult(i, 1)
        }
    }

    private fun configuraRecyclerView() {
        adapter = ListaNotasAdapter(this, notas)
        lista_notas.adapter = adapter
    }

    private fun criaNotasDeExemplo(dao: NotaDAO): MutableList<Nota> {
        val nota1 = Nota("Primeira Nota", "Descrição da nota")
        val nota2 = Nota("Segunda Nota", "Descrição beeeeeem maior que a descrição da primeira nota")
        dao.insere(nota1, nota2)
        return dao.todas()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if(requestCode == 1 && resultCode == 2 && data.hasExtra("nota")){
            val nota = data.getSerializableExtra("nota") as Nota
            adapter.adiciona(nota)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
