package com.example.postsapp.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postsapp.data.model.PostsResponseItem
import com.example.postsapp.data.source.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel:ViewModel() {
    private val _postsLiveData = MutableLiveData<List<PostsResponseItem>>()
    val  postsLiveData get() = _postsLiveData
    private val _messageLiveData = MutableLiveData<String>()
    val messageLiveData get() = _messageLiveData

    fun getPosts() {
        RetrofitClient.getServices()?.getPosts()?.enqueue(object : Callback<List<PostsResponseItem>> {
            override fun onResponse(
                call: Call<List<PostsResponseItem>>,
                response: Response<List<PostsResponseItem>>
            ) {
                response.let { postsResponse ->
                    if (postsResponse.isSuccessful)
                        _postsLiveData.value = postsResponse.body()
                }
            }

            override fun onFailure(call: Call<List<PostsResponseItem>>, t: Throwable) {
                t.let {
                    _messageLiveData.value = t.localizedMessage
                }
            }
        })
    }
}
