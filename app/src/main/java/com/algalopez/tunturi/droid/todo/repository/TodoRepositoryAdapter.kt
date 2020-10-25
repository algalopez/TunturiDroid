package com.algalopez.tunturi.droid.todo.repository

import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.model.Item

class TodoRepositoryAdapter(
    private val todoRepository: TodoRepository,
    private val todoMapper: TodoMapper
) : ITodoRepository {

    override suspend fun insertItem(item: Item) {
        return todoRepository.insertItem(item = todoMapper.fromDomain(item))
    }

    override suspend fun findAllItems(offset: Int, pageSize: Int): List<Item> {
        return todoRepository.getAllItems().map { todoMapper.toDomain(it) }
    }
}