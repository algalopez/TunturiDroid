package com.algalopez.tunturi.droid.todo.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(private val itemDao: ItemDao) {

    suspend fun getAllItems(): List<Item> = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Getting all items")
        return@withContext itemDao.getAllItems()
    }

    suspend fun getItemById(id: Int): Item = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Getting item by id")
        return@withContext itemDao.getItemById(id)
    }

    suspend fun insertItem(item: Item) = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Inserting item: $item")
        itemDao.insertItem(item)
    }

    suspend fun updateItem(item: Item) = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Updating item: $item")
        itemDao.updateItem(item)
    }

    suspend fun deleteItem(item: Item) = withContext(Dispatchers.IO) {
        Log.d(this@TodoRepository.toString(), "Deleting item: $item")
        itemDao.deleteItem(item)
    }
}
