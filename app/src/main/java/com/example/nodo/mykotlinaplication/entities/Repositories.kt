package com.example.nodo.mykotlinaplication.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by nodo on 28/09/17.
 */

class Repositories : Serializable {

     @SerializedName("items")
    var items: List<Repository>? = null
}
