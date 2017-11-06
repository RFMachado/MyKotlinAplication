package com.example.nodo.mykotlinaplication.feature.search.infrastructure

import com.example.nodo.mykotlinaplication.RepositoryInterface
import com.example.nodo.mykotlinaplication.feature.search.domain.SearchSource
import com.example.nodo.mykotlinaplication.feature.detail.domain.entities.Download
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import com.example.nodo.mykotlinaplication.feature.detail.infrastructure.mapper.DownloadMapper
import com.example.nodo.mykotlinaplication.feature.search.infrastructure.mapper.SearchMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by nodo on 31/10/17.
 */
class SearchInfrastructure @Inject constructor(private val source: RepositoryInterface): SearchSource {

    override fun fetchRepositories(text: String): Observable<List<Repository>> {
        return source.getRepos(text)
                .map { SearchMapper.map(it.items!!)}
    }



}