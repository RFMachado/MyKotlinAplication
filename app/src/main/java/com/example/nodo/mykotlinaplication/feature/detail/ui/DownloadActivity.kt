package com.example.nodo.mykotlinaplication.feature.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.nodo.mykotlinaplication.MyApplication
import com.example.nodo.mykotlinaplication.R
import com.example.nodo.mykotlinaplication.Utils.Extensions.hide
import com.example.nodo.mykotlinaplication.Utils.Extensions.show
import com.example.nodo.mykotlinaplication.feature.detail.presentation.DownloadView
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import com.example.nodo.mykotlinaplication.feature.detail.presentation.DownloadPresenter
import us.feras.mdv.MarkdownView
import javax.inject.Inject

/**
 * Created by nodo on 26/10/17.
 */
class DownloadActivity : AppCompatActivity(), DownloadView {

    @BindView(R.id.mark_down)
    lateinit var markdownView: MarkdownView

    @BindView(R.id.progress_md)
    lateinit var progressBar : ProgressBar


    lateinit var repository: Repository


    @Inject
    lateinit var presenter : DownloadPresenter

    companion object {
        private val EXTRA_DOWNLOAD = "download"

        fun launchIntent(context: Context, repository: Repository) = Intent(context, DownloadActivity::class.java).apply {
            putExtra(EXTRA_DOWNLOAD, repository)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        MyApplication.netComponent.inject(this)

        ButterKnife.bind(this)
        presenter.bind(this)

        repository = intent.getSerializableExtra(EXTRA_DOWNLOAD) as Repository

        presenter.fetchMarkdown(repository.login, repository.name)

    }

    override fun hideLoading() {
        progressBar.hide()
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun showResult(markdown: String) {
        markdownView.loadMarkdown(markdown)

    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, "No text", Toast.LENGTH_SHORT).show()
        finish()
        throwable.printStackTrace()
    }


    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

}