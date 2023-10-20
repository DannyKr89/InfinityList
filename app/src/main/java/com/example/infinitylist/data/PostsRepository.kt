package com.example.infinitylist.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.infinitylist.data.datasource.PostDataSource
import com.example.infinitylist.data.model.Children
import com.example.infinitylist.data.retrofit.PostApi

class PostsRepository {

    private val api = PostApi.getInstance()

    fun getPosts(): LiveData<PagingData<Children>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            )
        ) {
            PostDataSource(api)
        }.liveData
    }
}