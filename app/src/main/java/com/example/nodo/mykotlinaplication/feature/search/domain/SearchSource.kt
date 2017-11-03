package com.example.nodo.mykotlinaplication.feature.search.domain

import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Download
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities.RepositoryPayload
import io.reactivex.Observable

/**
 * Created by nodo on 31/10/17.
 */
interface SearchSource {
    fun fetchRepositories(text: String): Observable<List<Repository>>

    fun fetchDownload (login : String, name : String) : Observable<Download>
}