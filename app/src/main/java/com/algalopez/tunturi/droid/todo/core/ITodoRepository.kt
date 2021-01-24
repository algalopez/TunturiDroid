package com.algalopez.tunturi.droid.todo.core

import com.algalopez.tunturi.droid.todo.core.model.Item

interface ITodoRepository {

    suspend fun findAllItems(): List<Item>

    suspend fun insertItem(item: Item)

    suspend fun updateItem(item: Item)

    suspend fun removeItem(item: Item)
}
