package com.algalopez.tunturi.droid

import com.algalopez.tunturi.droid.todo.core.GetRootItemsActor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.presentation.TodoListViewModel
import com.algalopez.tunturi.droid.todo.repository.TodoMapper
import com.algalopez.tunturi.droid.todo.repository.TodoRepository
import com.algalopez.tunturi.droid.todo.repository.TodoRepositoryAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.mapstruct.factory.Mappers

val dependencyModuleList = module {

    single { Mappers.getMapper(TodoMapper::class.java) } bind TodoMapper::class

    single { TodoRepository(get()) } bind ITodoRepository::class
    single { TodoRepositoryAdapter(get(), get()) } bind ITodoRepository::class
    single { GetRootItemsActor(get()) }

    viewModel { TodoListViewModel(get()) }
}
