package com.dmagdaleno.ceep.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.helper.ItemTouchHelper
import com.dmagdaleno.ceep.dao.NotaDAO
import com.dmagdaleno.ceep.model.Nota
import com.dmagdaleno.ceep.R
import com.dmagdaleno.ceep.constants.Extras
import com.dmagdaleno.ceep.constants.RequestCode
import com.dmagdaleno.ceep.ui.rv.adapter.ListaNotasAdapter
import com.dmagdaleno.ceep.ui.rv.helper.callback.NotaItemTouchHelperCallback
import kotlinx.android.synthetic.main.activity_lista_notas.*

class ListaNotasActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ListaNotasActivity"
    }

    private lateinit var adapter: ListaNotasAdapter

    private lateinit var notas: MutableList<Nota>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        val dao = NotaDAO()

        notas = criaNotasDeExemplo(dao)

        configuraRecyclerView()

        lista_notas_insere_nota.setOnClickListener {
            abreFormularioNota(RequestCode.FORM_SALVA_NOTA)
        }
    }

    private fun abreFormularioNota(requestCode: Int,
                                   posicao: Int? = null,
                                   nota: Nota? = null) {

        val i = Intent(this, FormularioNotaActivity::class.java)
        posicao?.let { i.putExtra(Extras.POSICAO, it) }
        nota?.let { i.putExtra(Extras.NOTA, it) }
        startActivityForResult(i, requestCode)
    }

    private fun configuraRecyclerView() {
        adapter = ListaNotasAdapter(this, notas)

        adapter.onItemClick = { posicao, nota ->
            abreFormularioNota(RequestCode.FORM_EDITA_NOTA, posicao, nota)
        }

        lista_notas.adapter = adapter

        val helper = ItemTouchHelper(NotaItemTouchHelperCallback())
        helper.attachToRecyclerView(lista_notas)
    }

    private fun criaNotasDeExemplo(dao: NotaDAO): MutableList<Nota> {
        for(i in 1..10){
            val nota = Nota("Nota n.$i", "Descrição da nota")
            dao.insere(nota)
        }
        return dao.todas()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when {
            menuSalvaNota(requestCode, resultCode, data) -> {
                adicionaNotaAoAdapter(data)
            }
            menuEditaNota(requestCode, resultCode, data) -> {
                editaNotaDoAdapter(data)
            }
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun adicionaNotaAoAdapter(data: Intent) {
        val nota = data.getSerializableExtra(Extras.NOTA) as Nota
        adapter.adiciona(nota)
    }

    private fun editaNotaDoAdapter(data: Intent) {
        val posicao = data.getSerializableExtra(Extras.POSICAO) as Int
        val nota = data.getSerializableExtra(Extras.NOTA) as Nota
        adapter.altera(posicao, nota)
    }

    private fun menuSalvaNota(requestCode: Int, resultCode: Int, data: Intent) =
            requestCode == RequestCode.FORM_SALVA_NOTA &&
            resultCode  == Activity.RESULT_OK &&
            data.hasExtra(Extras.NOTA)

    private fun menuEditaNota(requestCode: Int, resultCode: Int, data: Intent) =
            requestCode == RequestCode.FORM_EDITA_NOTA &&
            resultCode  == Activity.RESULT_OK &&
            data.hasExtra(Extras.NOTA) &&
            data.hasExtra(Extras.POSICAO)
}
