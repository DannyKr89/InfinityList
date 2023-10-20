package com.example.infinitylist.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.infinitylist.data.model.Children
import com.example.infinitylist.data.retrofit.PostApi
import retrofit2.await

class PostDataSource(private val api: PostApi) : PagingSource<Int, Children>() {
    private var after = ""
    override fun getRefreshKey(state: PagingState<Int, Children>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Children> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        val posts = api.getPosts(pageSize, after)
        val children = posts.await().data.children

        after = children.last().data.name
        return try {
            LoadResult.Page(
                children,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (children.size < pageSize) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}