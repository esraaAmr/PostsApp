package com.example.postsapp.ui.posts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.data.model.PostsResponseItem
import com.example.postsapp.databinding.ItemPostLayoutBinding
import com.example.postsapp.ui.posts.PostViewModel

class PostsAdapter: RecyclerView.Adapter<PostsAdapter.postsViewHolder>() {
    private var posts:List<PostsResponseItem>? = null
    fun addPosts(posts:List<PostsResponseItem>){
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postsViewHolder {
        val binding = ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return postsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: postsViewHolder, position: Int) {
        with(holder){
            binding.apply {
                postTitleText.text = posts?.get(position)?.title
                postIdText.text = posts?.get(position)?.id.toString()
            }
        }
    }

    override fun getItemCount(): Int= posts?.size ?: 0
    class postsViewHolder(val binding: ItemPostLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}