package com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by nodo on 28/09/17.
 */

class RepositoryPayload : Serializable {

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("url")
    lateinit var url: String

    @SerializedName("owner")
    lateinit var owner: OwnerPayload
}
