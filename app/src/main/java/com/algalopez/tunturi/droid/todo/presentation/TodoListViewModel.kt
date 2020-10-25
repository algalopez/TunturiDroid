package com.algalopez.tunturi.droid.todo.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algalopez.tunturi.droid.todo.core.GetRootItemsActor
import com.algalopez.tunturi.droid.todo.core.TodoResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val TAG: String = "EchoViewModel"

class TodoListViewModel(private val getRootItemsActor: GetRootItemsActor) : ViewModel() {

    private val echoResponse = MutableLiveData<TodoResponse>()

    fun getEchoResponse(): LiveData<TodoResponse> {

        Log.d(TAG, "getting livedata response")
        return echoResponse
    }

    fun sendMessage(message: String) {

        Log.d(TAG, "Sending something")

        viewModelScope.launch {
            val flow = getRootItemsActor.run(message)
            flow.collect { response ->
                Log.d(TAG, response.toString())
                echoResponse.value = response

            }
        }
    }

}
