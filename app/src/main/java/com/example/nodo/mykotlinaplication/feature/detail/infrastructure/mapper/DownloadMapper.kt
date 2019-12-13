package com.example.nodo.mykotlinaplication.feature.detail.infrastructure.mapper

import com.example.nodo.mykotlinaplication.feature.detail.infrastructure.entities.DownloadPayload
import com.example.nodo.mykotlinaplication.feature.detail.domain.entities.Download

/**
 * Created by nodo on 03/11/17.
 */
object DownloadMapper {

    fun map(payload: DownloadPayload) = Download().apply {

        urldonwload = payload.urldonwload
    }
}