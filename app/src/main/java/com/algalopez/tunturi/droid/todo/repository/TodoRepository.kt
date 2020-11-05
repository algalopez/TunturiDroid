package com.algalopez.tunturi.droid.todo.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(private val itemDao: ItemDao) {

    suspend fun insertItem(item: Item) = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Inserting item: $item")
        itemDao.insertItem(item)
    }

    suspend fun getAllItems(): List<Item> = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Getting all items")
        return@withContext itemDao.getAllItems()
    }

    suspend fun getItemById(id: Int): Item = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Getting item by id")
        return@withContext itemDao.getItemById(id)
    }
}
