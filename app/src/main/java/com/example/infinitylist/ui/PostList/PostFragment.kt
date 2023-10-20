package com.example.infinitylist.ui.PostList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.infinitylist.R
import com.example.infinitylist.databinding.FragmentPostBinding

class PostFragment : Fragment(R.layout.fragment_post) {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PostViewModel

    private val adapter = PostAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPostBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(PostViewModel::class.java)

        viewModel.getPosts().observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }


        binding.apply {
            rvPosts.adapter = adapter
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}