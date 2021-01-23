package com.algalopez.tunturi.droid.todo.repository.adapter

import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.model.Item
import com.algalopez.tunturi.droid.todo.repository.TodoRepository

class TodoRepositoryAdapter(
    private val todoRepository: TodoRepository,
    private val todoMapper: TodoMapper
) : ITodoRepository {

    override suspend fun insertItem(item: Item) {
        return todoRepository.insertItem(item = todoMapper.fromDomain(item))
    }

    // TODO: add offset and pageSize
    override suspend fun findAllItems(): List<Item> {
        return todoRepository.getAllItems().map { todoMapper.toDomain(it) }
    }

    override suspend fun removeItem(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(item: Item) {
        TODO("Not yet implemented")
    }
}