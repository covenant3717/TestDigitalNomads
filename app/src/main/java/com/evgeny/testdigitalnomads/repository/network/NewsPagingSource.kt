package com.evgeny.testdigitalnomads.repository.network

import androidx.paging.PagingSource
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.repository.toListDBNews
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Evgeny Kuksov 10.08.2020
 */
class NewsPagingSource(private val mainApi: MainApi) : PagingSource<Int, DBNews>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DBNews> {

        val nextPage = params.key ?: 1

        return try {
            val response = mainApi.getNews(nextPage)
            val repos = response.articles.toListDBNews()
            LoadResult.Page(
                data = repos,
                prevKey = null,
                nextKey = if (repos.isEmpty()) null else nextPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


}