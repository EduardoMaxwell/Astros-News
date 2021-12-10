package br.com.eduardomaxwell.astranovos.presentation.ui.home

import androidx.lifecycle.*
import br.com.eduardomaxwell.astranovos.core.RemoteException
import br.com.eduardomaxwell.astranovos.core.State
import br.com.eduardomaxwell.astranovos.data.model.Post
import br.com.eduardomaxwell.astranovos.data.repository.PostRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * Essa classe dá suporte à tela principal (Home).
 */
class HomeViewModel(private val repository: PostRepository) : ViewModel() {

    private val _listPost = MutableLiveData<State<List<Post>>>()
    val listPost: LiveData<State<List<Post>>>
        get() = _listPost

    private val _progressBarVisible = MutableLiveData<Boolean>(false)
    val progressBarVisible: LiveData<Boolean> get() = _progressBarVisible


    private val _snackbar = MutableLiveData<String?>(null)
    val snackbar: LiveData<String?> get() = _snackbar

    fun showProgressBar() {
        _progressBarVisible.value = true
    }

    fun hideProgressBar() {
        _progressBarVisible.value = false
    }


    fun onSnackBarShown() {
        _snackbar.value = null
    }

    init {
        fetchPosts()
    }

    /**
     * Esse método coleta o fluxo do repositorio e atribui
     * o seu valor ao campo _listPost
     */
    private fun fetchPosts() {
        viewModelScope.launch {
            repository.listPosts()
                .onStart {
                    //faz algo no começo do flow
                    _listPost.postValue(State.Loading)
                    delay(800)
                }
                .catch {
                    //trata uma exceção aqui
                    val exception = RemoteException("Impossível conectar à API")
                    _listPost.postValue(State.Error(exception))
                    _snackbar.postValue(exception.message)
                }
                .collect { listPost ->
                    _listPost.postValue(State.Success(listPost))
                }
        }
    }

    val helloText = Transformations.map(listPost) {
        when (it) {
            State.Loading -> "🚀 Carregando últimas notícias"
            is State.Error -> "Vish, o foguete deu ré!"
            else -> ""
        }
    }

}
