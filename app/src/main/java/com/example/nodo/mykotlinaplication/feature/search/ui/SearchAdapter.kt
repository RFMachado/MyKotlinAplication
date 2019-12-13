package com.example.nodo.mykotlinaplication.feature.search.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nodo.mykotlinaplication.feature.detail.ui.DownloadActivity
import com.example.nodo.mykotlinaplication.R
import com.example.nodo.mykotlinaplication.feature.search.domain.entities.Repository
import kotlinx.android.synthetic.main.line_recyclerview.view.*

/**
 * Created by nodo on 24/10/17.
 */
class SearchAdapter(var repositories: List<Repository>) : androidx.recyclerview.widget.RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.line_recyclerview, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val repository = repositories[position]
        val context = holder.itemView.context

        holder.itemView.apply {
            login.text = repository.id
            id_rv.text = repository.login
        }

        Glide.with(context)
                .load(repository.avatarUrl)
                .apply(RequestOptions.circleCropTransform()) // Crop sobre a imagem
                .into(holder.itemView.avatar_url)

        holder.itemView.setOnClickListener {
            val intent = DownloadActivity.launchIntent(context, repository)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)
}