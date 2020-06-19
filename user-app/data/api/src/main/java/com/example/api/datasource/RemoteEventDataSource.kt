package com.example.api.datasource

import com.example.api.ThikiraApi
import com.example.error.ErrorHandler
import com.example.model.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteEventDataSource {
    suspend fun getEventList(): List<Event>
}

class RemoteEventDataSourceImpl (
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteEventDataSource {

    override suspend fun getEventList(): List<Event> = withContext(ioDispatcher) {
        try {
            thikiraApi.getEventList()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}