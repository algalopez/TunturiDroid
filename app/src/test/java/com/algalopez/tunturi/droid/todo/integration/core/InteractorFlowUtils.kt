package com.algalopez.tunturi.droid.todo.integration.core

import com.algalopez.tunturi.droid.todo.core.TodoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.junit.Assert.fail


suspend fun getFlowForQuerySuccess(flow: Flow<TodoResponse>): TodoResponse.QuerySuccess? {
    var receivedQuerySuccess = false
    var response: TodoResponse.QuerySuccess? = null
    flow.collect { element ->
        when (element) {
            is TodoResponse.Loading -> if (receivedQuerySuccess) fail()
            is TodoResponse.Error -> fail()
            is TodoResponse.CommandSuccess -> fail()
            is TodoResponse.QuerySuccess -> if (receivedQuerySuccess) {
                fail()
            } else {
                receivedQuerySuccess = true
                response = element
            }
        }
    }
    return response
}

suspend fun getFlowForCommandSuccess(flow: Flow<TodoResponse>): TodoResponse.CommandSuccess? {
    var receivedCommandSuccess = false
    var response: TodoResponse.CommandSuccess? = null

    flow.collect { element ->
        when (element) {
            is TodoResponse.Error -> fail()
            is TodoResponse.QuerySuccess -> fail()
            is TodoResponse.Loading -> if (receivedCommandSuccess) fail()
            is TodoResponse.CommandSuccess -> {
                if (receivedCommandSuccess) {
                    fail()
                } else {
                    receivedCommandSuccess = true
                    response = element
                }
            }
        }
    }
    return response
}

suspend fun getFlowForError(flow: Flow<TodoResponse>): TodoResponse.Error? {
    var receivedError = false
    var response: TodoResponse.Error? = null
    flow.collect { element ->
        when (element) {
            is TodoResponse.Loading -> if (receivedError) fail()
            is TodoResponse.Error -> {
                receivedError = true
                response = element
            }
            is TodoResponse.CommandSuccess -> fail()
            is TodoResponse.QuerySuccess -> fail()
        }
    }
    return response
}
