package com.algalopez.tunturi.droid.todo.core

import com.algalopez.tunturi.droid.todo.core.model.Item

interface ITodoRepository {

    suspend fun insertItem(item: Item)

    suspend fun findAllItems(offset: Int, pageSize: Int): List<Item>
}
