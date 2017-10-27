package com.example.nodo.mykotlinaplication.dagger.component

import com.example.nodo.mykotlinaplication.DetailActivity
import com.example.nodo.mykotlinaplication.MainActivity
import com.example.nodo.mykotlinaplication.dagger.module.AppModule
import com.example.nodo.mykotlinaplication.dagger.module.NetModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by nodo on 25/10/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface NetComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}