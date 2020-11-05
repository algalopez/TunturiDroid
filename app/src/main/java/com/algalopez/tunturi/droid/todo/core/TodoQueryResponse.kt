package com.algalopez.tunturi.droid.todo.core

import com.algalopez.tunturi.droid.todo.core.model.Item

sealed class TodoQueryResponse {

    data class Error(val errorMessage: String) : TodoQueryResponse()

    data class Success(val itemList: List<Item>) : TodoQueryResponse()

    data class Loading(val percentage: Int) : TodoQueryResponse()
}

sealed class TodoCommandResponse {

    data class Error(val errorMessage: String) : TodoCommandResponse()

    object Success : TodoCommandResponse()

    data class Loading(val percentage: Int) : TodoCommandResponse()
}

