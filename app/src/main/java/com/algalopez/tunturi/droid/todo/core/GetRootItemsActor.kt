package com.algalopez.tunturi.droid.todo.core

import android.util.Log
import com.algalopez.tunturi.droid.common.BaseInteractor
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRootItemsActor(
    private val todoRepository: ITodoRepository
) : BaseInteractor<String, TodoResponse>() {

    /**
     * Asdasd
     *
     * @param request asdasd
     * @return asdasd
     */
    override suspend fun run(request: String): Flow<TodoResponse> = flow {

        Log.d(this@GetRootItemsActor.toString(), "Executing echo actor")

        emit(TodoResponse.Loading(33))
        emit(TodoResponse.Loading(66))

        val itemList: List<Item> = todoRepository.findAllItems(0, 10)

        emit(TodoResponse.Success(itemList))
    }
}
