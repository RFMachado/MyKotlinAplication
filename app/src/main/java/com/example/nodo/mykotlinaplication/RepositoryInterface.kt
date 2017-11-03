package com.example.nodo.mykotlinaplication

import com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities.DownloadPayload
import com.example.nodo.mykotlinaplication.feature.search.infrastructure.entities.RepositoriesPayload
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by nodo on 25/10/17.
 */
interface RepositoryInterface {

    @GET("search/repositories")
    fun getRepos(@Query("q") sort: String): Observable<RepositoriesPayload>

    @GET("repos/{first}/{second}/readme")
    fun getDownload(@Path("first") first: String, @Path("second") second: String): Observable<DownloadPayload>

}
