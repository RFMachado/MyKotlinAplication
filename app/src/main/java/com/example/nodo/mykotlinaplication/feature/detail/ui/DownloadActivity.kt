package com.example.nodo.mykotlinaplication.feature.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nodo.mykotlinaplication.MyApplication
import com.example.nodo.mykotlinaplication.R
import com.example.nodo.mykotlinaplication.Utils.Extensions.hide
import com.example.nodo.mykotlinaplication.Utils.Extensions.show
import com.example.nodo.mykotlinaplication.feature.detail.presentation.DownloadView
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import com.example.nodo.mykotlinaplication.feature.detail.presentation.DownloadPresenter
import kotlinx.android.synthetic.main.activity_download.*
import javax.inject.Inject

/**
 * Created by nodo on 26/10/17.
 */
class DownloadActivity : AppCompatActivity(), DownloadView {

    lateinit var repository: Repository

    @Inject
    lateinit var presenter: DownloadPresenter

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
        presenter.bind(this)

        repository = intent.getSerializableExtra(EXTRA_DOWNLOAD) as Repository

        presenter.fetchMarkdown(repository.login, repository.name)
    }

    override fun hideLoading() {
        progress_md.hide()
    }

    override fun showLoading() {
        progress_md.show()
    }

    override fun showResult(markdown: String) {
        mark_down.loadMarkdown(markdown)
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