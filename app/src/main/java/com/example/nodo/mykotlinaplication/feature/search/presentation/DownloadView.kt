package com.example.nodo.mykotlinaplication.feature.search.presentation

import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Download
import com.example.nodo.mykotlinaplication.feature.shared.LoadingView

/**
 * Created by nodo on 03/11/17.
 */
interface DownloadView : LoadingView {
    fun showResult(download: Download)
    fun showError(throwable: Throwable)
}