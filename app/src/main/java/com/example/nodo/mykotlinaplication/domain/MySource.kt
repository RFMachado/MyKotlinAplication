package com.example.nodo.mykotlinaplication.domain

import com.example.nodo.mykotlinaplication.entities.Repository
import io.reactivex.Single

/**
 * Created by nodo on 31/10/17.
 */
interface MySource {
    fun fetchNotifications(): Single<List<Repository>>
}