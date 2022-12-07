package com.example.postsapp.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsapp.R
import com.example.postsapp.databinding.FragmentPostsBinding
import com.example.postsapp.ui.posts.adapter.PostsAdapter


class PostsFragment : Fragment() {
    private var _binding:FragmentPostsBinding?=null
    private val binding get() = _binding!!
    private lateinit var postsAdapter:PostsAdapter
    lateinit var postViewModel : PostViewModel
    private fun setRecyclerView(){
        postsAdapter = PostsAdapter()
        binding.postsRecycler.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        postViewModel.getPosts()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPostsBinding.bind(view)
        setRecyclerView()
        observe()
    }
    private fun observe(){
        postViewModel.postsLiveData.observe(viewLifecycleOwner){ posts ->
            postsAdapter.addPosts(posts)
        }
        postViewModel.messageLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),""+it,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}