package br.com.eduardomaxwell.astranovos.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.eduardomaxwell.astranovos.data.model.Post
import br.com.eduardomaxwell.astranovos.data.repository.MockAPIService
import br.com.eduardomaxwell.astranovos.data.repository.PostRepository
import br.com.eduardomaxwell.astranovos.data.repository.PostRepositoryImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Essa classe dá suporte à tela principal (Home).
 */
class HomeViewModel(private val repository: PostRepository) : ViewModel() {

    private val _listPost = MutableLiveData<List<Post>>()
    val listPost: LiveData<List<Post>>
        get() = _listPost

    init {
        fetchPosts()
    }

    /**
     * Esse método coleta o fluxo do repositorio e atribui
     * o seu valor ao campo _listPost
     */
    private fun fetchPosts() {
        viewModelScope.launch {
            repository.listPosts().collect {
                _listPost.value = it
            }
        }
    }

}
