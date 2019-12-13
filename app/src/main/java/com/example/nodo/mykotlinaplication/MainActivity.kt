package com.example.nodo.mykotlinaplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.nodo.mykotlinaplication.entities.Repository
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repositoryInterface: RepositoryInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyApplication.netComponent.inject(this)

        var items: List<Repository> = ArrayList()

        val adapter = MyAdapter(items as ArrayList<Repository>)

        recyclerView.adapter = adapter

        RxTextView.textChanges(editText)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { text ->
                    if (TextUtils.isEmpty(text)) {
                        showEmptyState()
                        Observable.empty<Any>()
                    }

                    showLoading()
                    repositoryInterface.getRepos(text.toString())
                            .onErrorResumeNext(Observable.empty())
                            .flatMapIterable { it.items }
                            .toSortedList({ a, b -> a.owner.login.compareTo(b.owner.login) }
                            )
                            .toObservable()
                            .subscribeOn(Schedulers.io())
                }

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data ->
                            hideLoading()
                            adapter.repositories = data
                            adapter.notifyDataSetChanged()
                        },
                        { it.printStackTrace() }
                )

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun hideLoading() {
        loading.setVisibility(View.GONE)
        recyclerView.setVisibility(View.VISIBLE)
    }

    private fun showLoading() {
        recyclerView.setVisibility(View.GONE)
        loading.visibility = View.VISIBLE
        closeInput(editText)
    }

    private fun showEmptyState() {
        recyclerView.setVisibility(View.GONE)
        loading.visibility = View.GONE
    }

    fun closeInput(caller: View?) {
        if (caller == null)
            return

        caller.postDelayed({
            val imm = caller.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(caller.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }, 500)
    }

    override fun onPause() {
        super.onPause()
        editText.setText("")
    }
}
