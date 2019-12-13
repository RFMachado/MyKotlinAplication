package com.example.nodo.mykotlinaplication

import android.app.Application
import com.example.nodo.mykotlinaplication.dagger.component.DaggerNetComponent
import com.example.nodo.mykotlinaplication.dagger.component.NetComponent
import com.example.nodo.mykotlinaplication.dagger.module.AppModule
import com.example.nodo.mykotlinaplication.dagger.module.NetModule
import com.facebook.stetho.Stetho

/**
 * Created by nodo on 25/10/17.
 */
class MyApplication : Application() {

    companion object {
        lateinit var netComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        netComponent = DaggerNetComponent.builder()
                .netModule(NetModule("https://api.github.com/"))
                .appModule(AppModule(this))
                .build()
    }
}
