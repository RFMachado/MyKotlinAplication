package com.example.nodo.mykotlinaplication.feature.search.presentation

import com.example.nodo.mykotlinaplication.Utils.RxUtils
import com.example.nodo.mykotlinaplication.feature.search.domain.SearchSource
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Download
import com.example.nodo.mykotlinaplication.feature.shared.LoadingContent
import com.example.nodo.mykotlinaplication.feature.shared.ReactivePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by nodo on 03/11/17.
 */
class DownloadPresenter @Inject constructor(private val source: SearchSource) : ReactivePresenter<DownloadView>() {

    private val loadingContent = LoadingContent<Download>()

    override fun bind(view: DownloadView) {
        super.bind(view)

        disposables += loadingContent.bind(view)
    }

    fun fetchDownload(login : String, name : String) {
        disposables += source.fetchDownload(login, name)
                .onErrorResumeNext(Observable.empty())
                .compose(RxUtils.applySchedulers())
                .compose (loadingContent)
                .subscribe(
                        { view?.showResult(it) },
                        { view?.showError(it) }
                )

    }
}

