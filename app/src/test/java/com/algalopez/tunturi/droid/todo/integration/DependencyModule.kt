package com.algalopez.tunturi.droid.todo.integration

import com.algalopez.tunturi.droid.todo.core.actor.GetAllItemsActor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.actor.InsertItemActor
import com.algalopez.tunturi.droid.todo.presentation.TodoListViewModel
import com.algalopez.tunturi.droid.todo.repository.ItemDao
import com.algalopez.tunturi.droid.todo.repository.TodoMapper
import com.algalopez.tunturi.droid.todo.repository.TodoRepository
import com.algalopez.tunturi.droid.todo.repository.TodoRepositoryAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.mapstruct.factory.Mappers
import org.mockito.Mockito.mock


val ITEM_DAO_STUB: ItemDao = mock(ItemDao::class.java)

val dependencyModuleList = module {

    single { ITEM_DAO_STUB } bind ItemDao::class

    single { TodoRepository(get()) } bind TodoRepository::class
    single { Mappers.getMapper(TodoMapper::class.java) } bind TodoMapper::class

    single { TodoRepositoryAdapter(get(), get()) } bind ITodoRepository::class

    single { GetAllItemsActor(get()) }
    single { InsertItemActor(get()) }

    viewModel { TodoListViewModel(get(), get()) }
}
