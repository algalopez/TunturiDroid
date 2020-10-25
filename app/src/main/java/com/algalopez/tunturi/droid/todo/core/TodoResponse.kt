package com.algalopez.tunturi.droid.todo.core

import com.algalopez.tunturi.droid.todo.core.model.Item

sealed class TodoResponse {

    data class Error(val errorMessage: String) : TodoResponse()

    data class Success(val itemList: List<Item>) : TodoResponse()

    data class Loading(val percentage: Int) : TodoResponse()
}