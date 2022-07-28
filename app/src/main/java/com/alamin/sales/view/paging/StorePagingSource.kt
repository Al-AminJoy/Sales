package com.alamin.sales.view.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alamin.sales.model.data.Store
import com.alamin.sales.model.network.ApiInterface
import java.lang.Exception

class StorePagingSource(private val apiInterface: ApiInterface) : PagingSource<Int, Store>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Store> {
        return try {
            val position = params.key ?: 1
            val response = apiInterface.getStores(position)

            return LoadResult.Page(
                data = response.storeList,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.meta.total) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Store>): Int? {
        return state.anchorPosition?.let { anchorePosition ->
            val anchorePage = state.closestPageToPosition(anchorePosition)
            anchorePage?.prevKey?.plus(1) ?: anchorePage?.nextKey?.minus(1)
        }
    }
}