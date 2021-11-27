package br.com.eduardomaxwell.astranovos.presentation.di

import br.com.eduardomaxwell.astranovos.presentation.ui.home.HomeViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

// TODO 005: Criar uma função privada viewModelModule()
// TODO 006: Criar uma função load()

/*object torna nossa classe um Singleton*/
object PresentationModule {

    /*
    *funcao que chamaremos na class App
    * loadKoinModules recebe nossoas instancias
    */
    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        /*aqui dentro serão instanciados
        os objetos que passarmos . Koin tratará
        de instancia-los pra nós
        */
        return module {
            factory { HomeViewModel(get()) }
        }
    }
}