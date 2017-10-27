package com.example.nodo.mykotlinaplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.nodo.mykotlinaplication.entities.Download
import com.example.nodo.mykotlinaplication.entities.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import us.feras.mdv.MarkdownView
import javax.inject.Inject

/**
 * Created by nodo on 26/10/17.
 */
class DetailActivity : AppCompatActivity() {

    @BindView(R.id.mark_down)
    lateinit var markdownView: MarkdownView

    @BindView(R.id.progress_md)
    lateinit var progressBar : ProgressBar

    @Inject
    lateinit var repositoryInterface : RepositoryInterface

    lateinit var repository : Repository

    internal var compositeDisposable = CompositeDisposable()

    companion object {
        private val EXTRA_DOWNLOAD = "download"

        fun launchIntent(context: Context, repository: Repository) = Intent(context, DetailActivity::class.java).apply {
            putExtra(EXTRA_DOWNLOAD, repository)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        MyApplication.netComponent.inject(this)

        ButterKnife.bind(this)

        repository = intent.getSerializableExtra(EXTRA_DOWNLOAD) as Repository

        val disposable = repositoryInterface
                .getDownload(repository.owner.login, repository.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    val download : Download = data
                    markdownView.loadMarkdownFile(download.urldonwload)
                    progressBar.setVisibility(View.GONE)
                    markdownView.visibility = View.VISIBLE

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