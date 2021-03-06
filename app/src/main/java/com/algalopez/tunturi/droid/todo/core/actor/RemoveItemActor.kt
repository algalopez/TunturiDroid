package com.algalopez.tunturi.droid.todo.core.actor

import android.util.Log
import com.algalopez.tunturi.droid.common.BaseInteractor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.TodoResponse
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoveItemActor(
    private val todoRepository: ITodoRepository
) : BaseInteractor<Item, TodoResponse>() {

    /**
     * Remove item
     *
     * @param request: Item
     * @return Unit
     */
    override suspend fun run(request: Item): Flow<TodoResponse> = flow {

        Log.d(this@RemoveItemActor.toString(), "Executing actor")
        emit(TodoResponse.Loading(0))

        todoRepository.removeItem(request)
        emit(TodoResponse.CommandSuccess)
    }
}
