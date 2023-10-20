package com.example.infinitylist.ui.PostList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.infinitylist.data.model.Children
import com.example.infinitylist.databinding.ItemPostBinding

class PostAdapter : PagingDataAdapter<Children, PostAdapter.PostViewHolder>(CALLBACK) {

    class PostViewHolder(private val binding: ItemPostBinding) : ViewHolder(binding.root) {

        fun bind(children: Children) {
            binding.apply {
                postId.text = children.data.id
                postName.text = children.data.name
                postScore.text = children.data.score.toString()
                postTitle.text = children.data.title
                postCommentsNumber.text = children.data.numberOfComments.toString()
            }
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }
}

object CALLBACK : DiffUtil.ItemCallback<Children>() {
    override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem.data.id == newItem.data.id
    }

}
