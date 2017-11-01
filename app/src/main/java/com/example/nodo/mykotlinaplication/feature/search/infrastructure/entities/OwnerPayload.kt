package com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by nodo on 28/09/17.
 */

class OwnerPayload : Serializable {

    @SerializedName("login")
    lateinit var login: String


    @SerializedName("avatar_url")
    lateinit var avatarUrl: String

    @SerializedName("id")
    lateinit var id: String

}
