package com.example.nodo.mykotlinaplication.feature.search.infrastructure.mapper

import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities.RepositoryPayload

/**
 * Created by nodo on 31/10/17.
 */
object SearchMapper {

    fun map(payload: RepositoryPayload) = Repository().apply {

        id = payload.owner.id
        login = payload.owner.login
        avatarUrl = payload.owner.avatarUrl
        name = payload.name
    }

    fun map(payloads: List<RepositoryPayload>) = payloads.map { map(it) }
}