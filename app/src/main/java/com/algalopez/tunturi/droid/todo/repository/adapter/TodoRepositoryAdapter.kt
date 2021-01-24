package com.algalopez.tunturi.droid.todo.repository.adapter

import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.model.Item
import com.algalopez.tunturi.droid.todo.repository.TodoRepository

class TodoRepositoryAdapter(
    private val todoRepository: TodoRepository,
    private val todoMapper: TodoMapper
) : ITodoRepository {

    // TODO: add offset and pageSize
    override suspend fun findAllItems(): List<Item> {
        return todoRepository.getAllItems().map { todoMapper.toDomain(it) }
    }

    override suspend fun insertItem(item: Item) {
        return todoRepository.insertItem(item = todoMapper.fromDomain(item))
    }

    override suspend fun updateItem(item: Item) {
        return todoRepository.updateItem(item = todoMapper.fromDomain(item))
    }

    override suspend fun removeItem(item: Item) {
        return todoRepository.deleteItem(item = todoMapper.fromDomain(item))
    }
}
