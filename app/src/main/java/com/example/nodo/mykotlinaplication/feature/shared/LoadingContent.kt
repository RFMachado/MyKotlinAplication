package com.example.nodo.mykotlinaplication.feature.shared

import com.example.nodo.mykotlinaplication.feature.search.ui.SearchActivity
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by nodo on 31/10/17.
 */
class LoadingContent<T> : ObservableTransformer<T, T>, SingleTransformer<T, T>, CompletableTransformer {
    private val show = PublishSubject.create<Unit>()
    private val hide = PublishSubject.create<Unit>()

    fun bind(view: LoadingView): Disposable {
        val composite = CompositeDisposable()

        composite.add(show.observeOn(AndroidSchedulers.mainThread()).subscribe { unit -> view.showLoading() })
        composite.add(hide.observeOn(AndroidSchedulers.mainThread()).subscribe { unit -> view.hideLoading() })

        return composite
    }

    override fun apply(@NonNull upstream: Observable<T>): ObservableSource<T> {
        return upstream
                .doOnSubscribe { onSubscribe -> showLoading() }
                .doFinally{ this.hiddenLoading() }
    }

    override fun apply(@NonNull upstream: Single<T>): SingleSource<T> {
        return upstream
                .doOnSubscribe { onSubscribe -> showLoading() }
                .doFinally{ this.hiddenLoading() }
    }

    override fun apply(@NonNull upstream: Completable): CompletableSource {
        return upstream
                .doOnSubscribe { onSubscribe -> showLoading() }
                .doFinally{ this.hiddenLoading() }
    }

    private fun showLoading() {
        show.onNext(Unit)
    }

    private fun hiddenLoading() {
        hide.onNext(Unit)
    }

}