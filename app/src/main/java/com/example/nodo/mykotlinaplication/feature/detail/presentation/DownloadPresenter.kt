package com.example.nodo.mykotlinaplication.feature.detail.presentation

import com.example.nodo.mykotlinaplication.Utils.RxUtils
import com.example.nodo.mykotlinaplication.feature.detail.domain.DownloadSource
import com.example.nodo.mykotlinaplication.feature.shared.LoadingContent
import com.example.nodo.mykotlinaplication.feature.shared.ReactivePresenter
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

/**
 * Created by nodo on 03/11/17.
 */
class DownloadPresenter @Inject constructor(private val source: DownloadSource) : ReactivePresenter<DownloadView>() {

    private val loadingContent = LoadingContent<String>()

    override fun bind(view: DownloadView) {
        super.bind(view)

        disposables += loadingContent.bind(view)
    }

    fun fetchMarkdown(login: String, name: String) {
        disposables += source.fetchMarkdown(login, name)
                .compose(RxUtils.applySchedulers())
                .compose(loadingContent)
                .subscribe(
                        { view?.showResult(it) },
                        { view?.showError(it) }
                )

    }
}

