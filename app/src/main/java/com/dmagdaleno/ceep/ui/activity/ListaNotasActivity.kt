package com.dmagdaleno.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dmagdaleno.ceep.dao.NotaDAO
import com.dmagdaleno.ceep.model.Nota
import com.dmagdaleno.ceep.R
import com.dmagdaleno.ceep.constants.Extras
import com.dmagdaleno.ceep.constants.RequestCode
import com.dmagdaleno.ceep.constants.ResultCode
import com.dmagdaleno.ceep.ui.rv.adapter.ListaNotasAdapter
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
            val i = Intent(this, FormularioNotaActivity::class.java)
            startActivityForResult(i, RequestCode.FORM_SALVA_NOTA)
        }
    }

    private fun configuraRecyclerView() {
        adapter = ListaNotasAdapter(this, notas)

        adapter.onItemClick = { nota ->
            Log.d(TAG, "Clicou em ${nota.titulo}")
            val i = Intent(this, FormularioNotaActivity::class.java)
            i.putExtra(Extras.NOTA, nota)
            startActivityForResult(i, RequestCode.FORM_EDITA_NOTA)
        }

        lista_notas.adapter = adapter
    }

    private fun criaNotasDeExemplo(dao: NotaDAO): MutableList<Nota> {
        for(i in 1..10){
            val nota1 = Nota("Nota n.$i", "Descrição da nota")
            dao.insere(nota1)
        }
        return dao.todas()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when {
            menuSalvaNota(requestCode, resultCode, data) -> {
                adicionaNotaAoAdapter(data)
            }
            menuEditaNota(requestCode, resultCode, data) -> {
                adicionaNotaAoAdapter(data)
            }
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun adicionaNotaAoAdapter(data: Intent) {
        val nota = data.getSerializableExtra(Extras.NOTA) as Nota
        adapter.adiciona(nota)
    }

    private fun menuSalvaNota(requestCode: Int, resultCode: Int, data: Intent) =
            requestCode == RequestCode.FORM_SALVA_NOTA &&
            resultCode  == ResultCode.SALVA_NOTA &&
            data.hasExtra(Extras.NOTA)

    private fun menuEditaNota(requestCode: Int, resultCode: Int, data: Intent) =
            requestCode == RequestCode.FORM_EDITA_NOTA &&
                    resultCode  == ResultCode.SALVA_NOTA &&
                    data.hasExtra(Extras.NOTA)
}
