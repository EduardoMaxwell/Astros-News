package br.com.eduardomaxwell.astranovos.core

import br.com.eduardomaxwell.astranovos.data.model.Post

sealed class State<out T : Any> {

    object Loading : State<Nothing>()

    data class Success<out T : Any>(val result: T) : State<T>()

    data class Error(val error: Throwable) : State<Nothing>()

}