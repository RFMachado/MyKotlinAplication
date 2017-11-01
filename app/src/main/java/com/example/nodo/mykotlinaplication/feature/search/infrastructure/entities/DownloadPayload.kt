package com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by nodo on 27/10/17.
 */
class DownloadPayload : Serializable{

    @SerializedName("download_url")
    lateinit var urldonwload: String

}