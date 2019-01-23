package com.dmagdaleno.ceep.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.dmagdaleno.ceep.R
import com.dmagdaleno.ceep.dao.NotaDAO
import com.dmagdaleno.ceep.model.Nota
import kotlinx.android.synthetic.main.activity_formulario_nota.*

class FormularioNotaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_nota)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_salva_nota, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_formulario_ic_salva_nota -> {
                val titulo = formulario_nota_titulo.text.toString()
                val descricao = formulario_nota_descricao.text.toString()
                val nota = Nota(titulo, descricao)
                val resultado = Intent()
                resultado.putExtra("nota", nota)
                setResult(2, resultado)
                finish()
            }
            else -> { }
        }
        return super.onOptionsItemSelected(item)
    }
}
