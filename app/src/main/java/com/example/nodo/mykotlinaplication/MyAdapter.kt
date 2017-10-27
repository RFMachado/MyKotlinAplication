package com.example.nodo.mykotlinaplication

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nodo.mykotlinaplication.entities.Repository
import kotlinx.android.synthetic.main.line_recyclerview.view.*
import java.io.Serializable


/**
 * Created by nodo on 24/10/17.
 */
class MyAdapter (var repositories: List<Repository>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.line_recyclerview, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val repository = repositories[position]
        val context = holder.itemView.context


        holder.itemView.apply {
            login.text = repository.owner.id
            id_rv.text = repository.owner.login
        }

        Glide.with(context)
                .load(repository.owner.avatarUrl)
                .apply(RequestOptions.circleCropTransform())  //Crop sobre a imagem
                .into(holder.itemView.avatar_url)


        holder.itemView.setOnClickListener {
            val intent = DetailActivity.launchIntent(context, repository)
            context.startActivity(intent)
        }

        holder.bindItems(repositories)
    }


    override fun getItemCount(): Int {
        return repositories.size
    }


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(repositories: List<Repository>) {
            val id = itemView.login
            val login = itemView.id_rv
            val avatarurl = itemView.avatar_url

        }

    }

}