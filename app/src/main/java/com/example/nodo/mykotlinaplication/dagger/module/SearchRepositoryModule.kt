package com.example.nodo.mykotlinaplication.dagger.module

import com.example.nodo.mykotlinaplication.feature.detail.domain.DownloadSource
import com.example.nodo.mykotlinaplication.feature.detail.infrastructure.DownloadInfrastructure
import com.example.nodo.mykotlinaplication.feature.search.domain.SearchSource
import com.example.nodo.mykotlinaplication.feature.search.infrastructure.SearchInfrastructure
import dagger.Module
import dagger.Provides

/**
 * Created by nodo on 01/11/17.
 */
@Module
class SearchRepositoryModule {

    @Provides
    fun provideSearchSource(searchInfrastructure: SearchInfrastructure): SearchSource {
        return searchInfrastructure
    }

    @Provides
    fun provideDownloadSource(downloadInfrastructure: DownloadInfrastructure): DownloadSource {
        return downloadInfrastructure
    }
}