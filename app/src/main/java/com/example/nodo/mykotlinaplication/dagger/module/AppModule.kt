package com.example.nodo.mykotlinaplication.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by nodo on 25/10/17.
 */
@Module
class AppModule(var mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }
}
