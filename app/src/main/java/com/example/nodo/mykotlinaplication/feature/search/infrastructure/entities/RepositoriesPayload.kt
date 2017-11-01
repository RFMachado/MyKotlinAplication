package com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by nodo on 28/09/17.
 */

class RepositoriesPayload : Serializable {

     @SerializedName("items")
    var items: List<RepositoryPayload>? = null
}
