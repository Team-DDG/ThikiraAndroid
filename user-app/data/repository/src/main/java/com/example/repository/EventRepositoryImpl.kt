package com.example.repository

import com.example.api.datasource.RemoteEventDataSource
import com.example.model.Event
import com.example.model.repository.EventRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRepositoryImpl(
    private val remoteEventDataSource: RemoteEventDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : EventRepository {
    override suspend fun getEventList(): List<Event> = withContext(ioDispatcher) {
        remoteEventDataSource.getEventList()
    }
}