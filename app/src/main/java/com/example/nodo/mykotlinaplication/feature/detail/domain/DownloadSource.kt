package com.example.nodo.mykotlinaplication.feature.detail.domain

import io.reactivex.Observable

/**
 * Created by nodo on 03/11/17.
 */
interface DownloadSource {

        fun fetchMarkdown(login: String, name: String): Observable<String>
}