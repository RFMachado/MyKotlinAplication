package com.example.nodo.mykotlinaplication.feature.search.domain.entities

import java.io.Serializable

/**
 * Created by nodo on 01/11/17.
 */
class Repository : Serializable {

    lateinit var id: String
    lateinit var login: String
    lateinit var avatarUrl: String
    lateinit var name: String
}