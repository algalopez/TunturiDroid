package com.algalopez.tunturi.droid.todo.core

import com.algalopez.tunturi.droid.todo.core.model.Item

sealed class TodoResponse {

    data class Error(val errorMessage: String) : TodoResponse()

    data class QuerySuccess(val itemList: List<Item>) : TodoResponse()

    object CommandSuccess : TodoResponse()

    data class Loading(val percentage: Int) : TodoResponse()
}
