package com.example.nodo.mykotlinaplication.feature.search.domain

import com.example.nodo.mykotlinaplication.feature.detail.domain.entities.Download
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import io.reactivex.Observable

/**
 * Created by nodo on 31/10/17.
 */
interface SearchSource {

    fun fetchRepositories(text: String): Observable<List<Repository>>

}