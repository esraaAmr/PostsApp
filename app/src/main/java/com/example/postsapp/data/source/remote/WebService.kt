package com.example.postsapp.data.source.remote

import com.example.postsapp.data.model.PostsResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("posts")
    fun getPosts(): Call<List<PostsResponseItem>>

}