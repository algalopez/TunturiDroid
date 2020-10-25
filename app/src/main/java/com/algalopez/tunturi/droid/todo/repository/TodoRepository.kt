package com.algalopez.tunturi.droid.todo.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(private val itemDao: ItemDao) {

    suspend fun insertItem(item: Item) = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Inserting message: $item")
        itemDao.insertItem(item)
    }

    suspend fun getAllItems(): List<Item> = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Getting all messages")
        return@withContext itemDao.getAllItems()
    }
}
