package com.algalopez.tunturi.droid.todo.core.actor

import android.util.Log
import com.algalopez.tunturi.droid.common.BaseInteractor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.TodoCommandResponse
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertItemActor(
    private val todoRepository: ITodoRepository
) : BaseInteractor<Item, TodoCommandResponse>() {

    /**
     * Get all items
     *
     * @return All items
     */
    override suspend fun run(request: Item): Flow<TodoCommandResponse> = flow {

        Log.d(this@InsertItemActor.toString(), "Executing actor")

        emit(TodoCommandResponse.Loading(0))

        todoRepository.insertItem(request)

        emit(TodoCommandResponse.Success)
    }
}
