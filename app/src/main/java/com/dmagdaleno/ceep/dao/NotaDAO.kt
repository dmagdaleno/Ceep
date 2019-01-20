package com.dmagdaleno.ceep.dao

import com.dmagdaleno.ceep.model.Nota
import java.util.*

class NotaDAO {

    companion object {
        val notas = mutableListOf<Nota>()
    }

    fun todas() = notas

    fun insere(nota: Nota) {
        NotaDAO.notas.add(nota)
    }

    fun insere(vararg notas: Nota) {
        NotaDAO.notas.addAll(notas)
    }

    fun altera(posicao: Int, nota: Nota) {
        NotaDAO.notas[posicao] = nota
    }

    fun troca(posicaoInicio: Int, posicaoFim: Int) {
        Collections.swap(NotaDAO.notas, posicaoInicio, posicaoFim)
    }

    fun remove(posicao: Int) {
        NotaDAO.notas.removeAt(posicao)
    }

    fun removeTodos() {
        NotaDAO.notas.clear()
    }

}