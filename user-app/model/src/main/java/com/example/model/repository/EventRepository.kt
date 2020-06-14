package com.example.model.repository

import com.example.model.Event

interface EventRepository {
    suspend fun getEventList(): List<Event>
}