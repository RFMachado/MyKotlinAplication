package com.example.nodo.mykotlinaplication.feature.shared

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by nodo on 31/10/17.
 */
open class ReactivePresenter<V> {
    val disposables = CompositeDisposable()
    var view: V? = null
        private set

    fun disposables() = disposables
    fun view() = view

    open fun bind(view: V) {
        this.view = view
    }

    fun unbind() {
        this.disposables.clear()
        this.view = null
    }

    protected val isBinded: Boolean
        get() = this.view != null
}