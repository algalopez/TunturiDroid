package com.algalopez.tunturi.droid.todo.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algalopez.tunturi.droid.todo.core.TodoCommandResponse
import com.algalopez.tunturi.droid.todo.core.actor.GetAllItemsActor
import com.algalopez.tunturi.droid.todo.core.TodoQueryResponse
import com.algalopez.tunturi.droid.todo.core.actor.InsertItemActor
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val TAG: String = "EchoViewModel"

class TodoListViewModel(
    private val getAllItemsActor: GetAllItemsActor,
    private val insertItemActor: InsertItemActor
) : ViewModel() {

    private val queryResponse = MutableLiveData<TodoQueryResponse>()
    private val commandResponse = MutableLiveData<TodoCommandResponse>()

    fun getQueryResponse(): LiveData<TodoQueryResponse> {
        Log.d(TAG, "getting live data query response")
        return queryResponse
    }

    fun getCommandResponse(): LiveData<TodoCommandResponse> {
        Log.d(TAG, "getting live data query response")
        return commandResponse
    }

    fun getAllItems() {
        Log.d(TAG, "Getting all items")

        viewModelScope.launch {
            val flow = getAllItemsActor.run(Unit)
            flow.collect { response ->
                Log.d(TAG, response.toString())
                queryResponse.value = response

            }
        }
    }

    fun insertItem(name: String, color: String) {
        Log.d(TAG, "Inserting item")

        viewModelScope.launch {
            val flow = insertItemActor.run(Item(id = null, name = name, color = color))
            flow.collect { response ->
                Log.d(TAG, response.toString())
                commandResponse.value = response
            }
        }
    }

}
