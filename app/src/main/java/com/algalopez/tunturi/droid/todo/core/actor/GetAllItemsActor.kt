package com.algalopez.tunturi.droid.todo.core.actor

import android.util.Log
import com.algalopez.tunturi.droid.common.BaseInteractor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.TodoResponse
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllItemsActor(
    private val todoRepositoryAdapter: ITodoRepository
) : BaseInteractor<Unit, TodoResponse>() {

    /**
     * Get all items
     *
     * @return All items
     */
    override suspend fun run(request: Unit): Flow<TodoResponse> = flow {

        Log.d(this@GetAllItemsActor.toString(), "Executing actor")

        emit(TodoResponse.Loading(0))

        val itemList: List<Item> = todoRepositoryAdapter.findAllItems()

        emit(TodoResponse.QuerySuccess(itemList))
    }
}
