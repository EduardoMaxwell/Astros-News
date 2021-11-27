package br.com.eduardomaxwell.astranovos

import android.app.Application
import br.com.eduardomaxwell.astranovos.data.di.DataModule
import br.com.eduardomaxwell.astranovos.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/*
* Primeira classe que será chamado
* ao criar o app
* */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

        }

        /*
        * carrega todos os modulo criados no object PresentationModule
        * */
        PresentationModule.load()

        /*
        * carrega todos os modulo criados no object DataModule
        * */
        DataModule.load()
    }
}