package com.evgeny.testdigitalnomads.util

import android.util.Log
import androidx.paging.PagedList
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.vippolis.employeecontrol.repository.Resource
import java.util.concurrent.Executors

class NewsBoundaryCallback() : PagedList.BoundaryCallback<DBNews>(), KoinComponent {

    private val repository by inject<Repository>()

    //==============================================================================================

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        mlg("FIRST LOAD")

        GlobalScope.launch(Dispatchers.IO) {
            repository.getNews(pageSize = 5) { response ->
                when (response) {
                    is Resource.Success -> {
                        mlg("success")
                    }
                    is Resource.Error -> mlg(response.errorMessage.toString())
                    is Resource.Progress -> mlg(response.isLoading.toString())
                }
            }
        }

/*
        //1
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { helperCallback ->
            api.getPosts()
                //2
                .enqueue(object : Callback<RedditApiResponse> {

                    override fun onFailure(call: Call<RedditApiResponse>?, t: Throwable) {
                        //3
                        Log.e("RedditBoundaryCallback", "Failed to load data!")
                        helperCallback.recordFailure(t)
                    }

                    override fun onResponse(
                        call: Call<RedditApiResponse>?,
                        response: Response<RedditApiResponse>
                    ) {
                        //4
                        val posts = response.body()?.data?.children?.map { it.data }
                        executor.execute {
                            db.postDao().insert(posts ?: listOf())
                            helperCallback.recordSuccess()
                        }
                    }
                })
        }
*/
    }

    override fun onItemAtEndLoaded(itemAtEnd: DBNews) {
        super.onItemAtEndLoaded(itemAtEnd)
        mlg("LOAD")

        GlobalScope.launch(Dispatchers.IO) {
            repository.getNews(pageSize = 5) { response ->
                when (response) {
                    is Resource.Success -> {
                        mlg("success")
                    }
                    is Resource.Error -> mlg(response.errorMessage.toString())
                    is Resource.Progress -> mlg(response.isLoading.toString())
                }
            }
        }

/*
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { helperCallback ->
            api.getPosts(after = itemAtEnd.key)
                .enqueue(object : Callback<RedditApiResponse> {

                    override fun onFailure(call: Call<RedditApiResponse>?, t: Throwable) {
                        Log.e("RedditBoundaryCallback", "Failed to load data!")
                        helperCallback.recordFailure(t)
                    }

                    override fun onResponse(
                        call: Call<RedditApiResponse>?,
                        response: Response<RedditApiResponse>
                    ) {

                        val posts = response.body()?.data?.children?.map { it.data }
                        executor.execute {
                            db.postDao().insert(posts ?: listOf())
                            helperCallback.recordSuccess()
                        }
                    }
                })
        }
*/
    }
}
