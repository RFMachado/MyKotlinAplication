package com.example.nodo.mykotlinaplication

import com.example.nodo.mykotlinaplication.entities.Download
import com.example.nodo.mykotlinaplication.entities.Repositories
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by nodo on 25/10/17.
 */
interface RepositoryInterface {

    @GET("search/repositories")
    fun getRepos(@Query("q") sort: String): Observable<Repositories>

    @GET("repos/{first}/{second}/readme")
    fun getDownload(@Path("first") first: String, @Path("second") second: String): Observable<Download>

}
