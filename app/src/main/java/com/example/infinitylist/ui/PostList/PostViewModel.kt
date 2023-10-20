package com.example.infinitylist.ui.PostList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.infinitylist.data.PostsRepository
import com.example.infinitylist.data.model.Children

class PostViewModel : ViewModel() {

    private val repository = PostsRepository()


    fun getPosts(): LiveData<PagingData<Children>> {
        return repository.getPosts().cachedIn(viewModelScope)
    }
}