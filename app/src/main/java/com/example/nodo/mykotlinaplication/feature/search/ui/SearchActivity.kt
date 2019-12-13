package com.example.nodo.mykotlinaplication.feature.search.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nodo.mykotlinaplication.MyApplication
import com.example.nodo.mykotlinaplication.R
import com.example.nodo.mykotlinaplication.Utils.Extensions.hide
import com.example.nodo.mykotlinaplication.Utils.Extensions.show
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import com.example.nodo.mykotlinaplication.feature.search.presentation.SearchPresenter
import com.example.nodo.mykotlinaplication.feature.search.presentation.SearchView
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchView {

    private var items = ArrayList<Repository>()

    @Inject
    lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyApplication.netComponent.inject(this)
        presenter.bind(this)

        setupRecycler()

        RxTextView.textChanges(editText)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe { presenter.fetchRepositories(it.toString()) }
    }

    override fun hideLoading() {
        loading.hide()
    }

    override fun showLoading() {
        loading.show()
    }

    override fun showResult(repositories: List<Repository>) {
        items.addAll(repositories)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun showError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    private fun setupRecycler() = with(recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = SearchAdapter(items)
    }
}