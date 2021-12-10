package br.com.eduardomaxwell.astranovos.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.eduardomaxwell.astranovos.core.State
import br.com.eduardomaxwell.astranovos.data.model.Post
import br.com.eduardomaxwell.astranovos.databinding.HomeFragmentBinding
import br.com.eduardomaxwell.astranovos.presentation.adapter.PostListAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

// Tarefas do PresentationModule

// Tarefas da classe App
// TODO 007: Criar uma classe App
// TODO 008: Adicionar o App ao manifest.xml

/**
 * Essa classe representa o fragmento da tela Home.
 */
class HomeFragment : Fragment() {

//    TODO 010: Usar o delegate Koin para injetar o HomeViewModel

    private val viewModel: HomeViewModel by viewModel()
    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        TODO 009: Eliminar essa inicialização manual do ViewModel

        initBinding()
        initSnackbar()
        initRecyclerView()
        return binding.root
    }

    private fun initSnackbar() {
        viewModel.snackbar.observe(viewLifecycleOwner){
            it?.let { errorMsg ->
                Snackbar.make(binding.root, errorMsg, Snackbar.LENGTH_LONG).show()
                viewModel.onSnackBarShown()
            }
        }
    }

    private fun initRecyclerView() {

        val adapter = PostListAdapter()
        binding.homeRv.adapter = adapter

        viewModel.listPost.observe(viewLifecycleOwner) { state ->
            when (state) {
                State.Loading -> viewModel.showProgressBar()
                is State.Error -> viewModel.hideProgressBar()
                is State.Success -> {
                    viewModel.hideProgressBar()
                    adapter.submitList(state.result)
                }
            }
        }


    }

    /**
     * Esse método faz a inicialização do DataBinding.
     * O arquivo XML possui uma variável viewModel, que precisa
     * ser vinculada ao ViewModel instanciado. Também é preciso
     * atribuir um LifeCycleOwner para que os bindings de live data
     * funcionem.
     */
    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }


    /**
     * Esse companion object é código boilerplate que provavelmente
     * não será usado.
     */
    companion object {
        fun newInstance() = HomeFragment()
    }

}