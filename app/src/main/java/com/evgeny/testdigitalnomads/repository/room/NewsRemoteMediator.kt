/*
package com.evgeny.testdigitalnomads.repository.room

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.repository.network.MainApi
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

*/
/**
 * Created by Evgeny Kuksov 10.08.2020
 *//*


@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val query: String, private val mainDao: MainDao, private val mainApi: MainApi
) : RemoteMediator<Int, DBNews>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, DBNews>
    ): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    if (remoteKeys == null) {
                        throw InvalidObjectException("Remote key and the prevKey should not be null")
                    }
                    val prevKey = remoteKeys.prevKey
                    if (prevKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = false)
                    }
                    remoteKeys.prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    if (remoteKeys?.nextKey == null) {
                        throw InvalidObjectException("Remote key should not be null for $loadType")
                    }
                    remoteKeys.nextKey
                }
            }

            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    lastItem.id
                }
            }

            val response = mainApi.getNews(
                page = loadKey
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDao.deleteByQuery(query)
                }

                userDao.insertAll(response.users)
            }

            MediatorResult.Success(endOfPaginationReached = response.nextKey == null)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}*/
