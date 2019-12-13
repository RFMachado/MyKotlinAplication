package com.example.nodo.mykotlinaplication.feature.search.presentation

import com.example.nodo.mykotlinaplication.Utils.RxUtils
import com.example.nodo.mykotlinaplication.feature.search.domain.SearchSource
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import com.example.nodo.mykotlinaplication.feature.shared.LoadingContent
import com.example.nodo.mykotlinaplication.feature.shared.ReactivePresenter
import io.reactivex.Observable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

/**
 * Created by nodo on 31/10/17.
 */
class SearchPresenter @Inject constructor(private val source: SearchSource) : ReactivePresenter<SearchView>() {

    private val loadingContent = LoadingContent<List<Repository>>()

    override fun bind(view: SearchView) {
        super.bind(view)
        disposables += loadingContent.bind(view)
    }

    fun fetchRepositories(text: String) {
        disposables += source.fetchRepositories(text)
                .onErrorResumeNext(Observable.empty())
                .flatMapIterable { it }
                .toSortedList { a, b -> a.login.compareTo(b.login) }
                .compose(RxUtils.applySingleSchedulers())
                .compose(loadingContent)
                .subscribe(
                        { view?.showResult(it) },
                        { view?.showError(it) }
                )
    }
}