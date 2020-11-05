package com.algalopez.tunturi.droid.todo.core.actor

import android.util.Log
import com.algalopez.tunturi.droid.common.BaseInteractor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.TodoQueryResponse
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllItemsActor(
    private val todoRepository: ITodoRepository
) : BaseInteractor<Unit, TodoQueryResponse>() {

    /**
     * Get all items
     *
     * @return All items
     */
    override suspend fun run(request: Unit): Flow<TodoQueryResponse> = flow {

        Log.d(this@GetAllItemsActor.toString(), "Executing actor")

        emit(TodoQueryResponse.Loading(0))

        val itemList: List<Item> = todoRepository.findAllItems()

        emit(TodoQueryResponse.Success(itemList))
    }
}
