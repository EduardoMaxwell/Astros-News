package br.com.eduardomaxwell.astranovos.data.di

import br.com.eduardomaxwell.astranovos.data.repository.MockAPIService
import br.com.eduardomaxwell.astranovos.data.repository.PostRepository
import br.com.eduardomaxwell.astranovos.data.repository.PostRepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/*
* Gerencia as dependencias da camada data
* */
object DataModule {

    fun load() {
        loadKoinModules(postModule())
    }

    private fun postModule(): Module {
        return module {
            single<PostRepository> { PostRepositoryImpl(get()) }

            single { MockAPIService }
        }
    }


}