package com.example.nodo.mykotlinaplication.feature.detail.infrastructure

import com.example.nodo.mykotlinaplication.RepositoryInterface
import com.example.nodo.mykotlinaplication.feature.detail.domain.DownloadSource
import com.example.nodo.mykotlinaplication.feature.detail.infrastructure.mapper.DownloadMapper
import io.reactivex.Observable
import kotlinx.android.synthetic.main.line_recyclerview.view.*
import javax.inject.Inject

/**
 * Created by nodo on 03/11/17.
 */

class DownloadInfrastructure @Inject constructor(private val source: RepositoryInterface) : DownloadSource {

    override fun fetchMarkdown(login: String, name: String): Observable<String> {
        return source.getDownload(login, name)
                .map { DownloadMapper.map(it) }
                .flatMap { source.getContentFile(it.urldonwload) }
    }
}