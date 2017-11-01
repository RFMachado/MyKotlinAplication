package com.example.nodo.mykotlinaplication.feature.search.presentation

import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities.RepositoryPayload
import com.example.nodo.mykotlinaplication.feature.shared.LoadingView

/**
 * Created by nodo on 31/10/17.
 */
interface SearchView: LoadingView {
    fun showResult(repositories: List<Repository>)
    fun showError(throwable: Throwable)
}