package com.example.nodo.mykotlinaplication.feature.detail.presentation

import com.example.nodo.mykotlinaplication.feature.shared.LoadingView

/**
 * Created by nodo on 03/11/17.
 */
interface DownloadView : LoadingView {
    fun showResult(markdown: String)
    fun showError(throwable: Throwable)
}