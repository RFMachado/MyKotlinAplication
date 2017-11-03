package com.example.nodo.mykotlinaplication.feature.search.infrastructure.mapper

import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Download
import com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities.DownloadPayload

/**
 * Created by nodo on 03/11/17.
 */
object DownloadMapper {

    fun map(payload: DownloadPayload) = Download().apply{

        urldonwload = payload.urldonwload

    }

}