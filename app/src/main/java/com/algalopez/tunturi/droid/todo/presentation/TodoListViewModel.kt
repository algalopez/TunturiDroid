package com.algalopez.tunturi.droid.todo.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algalopez.tunturi.droid.todo.core.actor.GetAllItemsActor
import com.algalopez.tunturi.droid.todo.core.TodoResponse
import com.algalopez.tunturi.droid.todo.core.actor.InsertItemActor
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val TAG: String = "EchoViewModel"

class TodoListViewModel(
    private val getAllItemsActor: GetAllItemsActor,
    private val insertItemActor: InsertItemActor
) : ViewModel() {

    private val todoResponse = MutableLiveData<TodoResponse>()

    fun getResponse(): LiveData<TodoResponse> {
        Log.d(TAG, "getting live data query response")
        return todoResponse
    }

    fun getAllItems() {
        Log.d(TAG, "Getting all items")

        viewModelScope.launch {
            val flow = getAllItemsActor.run(Unit)
            flow.collect { response ->
                Log.d(TAG, response.toString())
                todoResponse.value = response

            }
        }
    }

    fun insertItem(name: String, color: String) {
        Log.d(TAG, "Inserting item")

        viewModelScope.launch {
            val flow = insertItemActor.run(Item(id = null, name = name, color = color))
            flow.collect { response ->
                Log.d(TAG, response.toString())
                todoResponse.value = response
            }
        }
    }

}
