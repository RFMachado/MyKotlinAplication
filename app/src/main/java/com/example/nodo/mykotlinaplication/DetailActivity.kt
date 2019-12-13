package com.example.nodo.mykotlinaplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.nodo.mykotlinaplication.entities.DownloadPayload
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_download.*
import javax.inject.Inject

/**
 * Created by nodo on 26/10/17.
 */
class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var repositoryInterface: RepositoryInterface

    lateinit var repository: Repository

    internal var compositeDisposable = CompositeDisposable()

    companion object {
        private val EXTRA_DOWNLOAD = "download"

        fun launchIntent(context: Context, repository: Repository) = Intent(context, DetailActivity::class.java).apply {
            putExtra(EXTRA_DOWNLOAD, repository)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyApplication.netComponent.inject(this)

        repository = intent.getSerializableExtra(EXTRA_DOWNLOAD) as Repository

        val disposable = repositoryInterface
                .getDownload(repository.login, repository.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    val download: DownloadPayload? = data
                    mark_down.loadMarkdownFile(download?.urldonwload)
                    progress_md.setVisibility(View.GONE)
                    mark_down.visibility = View.VISIBLE
                },
                        {
                            Toast.makeText(this, "No text", Toast.LENGTH_SHORT).show()
                            finish()
                            it.printStackTrace()
                        })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}