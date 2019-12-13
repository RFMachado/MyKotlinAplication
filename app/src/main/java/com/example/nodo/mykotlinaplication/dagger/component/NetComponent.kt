package com.example.nodo.mykotlinaplication.dagger.component

import com.example.nodo.mykotlinaplication.DetailActivity
import com.example.nodo.mykotlinaplication.feature.detail.ui.DownloadActivity
import com.example.nodo.mykotlinaplication.feature.search.ui.SearchActivity
import com.example.nodo.mykotlinaplication.dagger.module.AppModule
import com.example.nodo.mykotlinaplication.dagger.module.NetModule
import com.example.nodo.mykotlinaplication.dagger.module.SearchRepositoryModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by nodo on 25/10/17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetModule::class,
        SearchRepositoryModule::class
))
interface NetComponent {

    fun inject(activity: SearchActivity)
    fun inject(activity: DownloadActivity)
    fun inject(detailActivity: DetailActivity)
}