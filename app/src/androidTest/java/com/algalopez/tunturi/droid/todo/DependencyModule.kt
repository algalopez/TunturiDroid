package com.algalopez.tunturi.droid.todo

import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.actor.GetAllItemsActor
import com.algalopez.tunturi.droid.todo.core.actor.InsertItemActor
import com.algalopez.tunturi.droid.todo.core.actor.RemoveItemActor
import com.algalopez.tunturi.droid.todo.core.actor.UpdateItemActor
import com.algalopez.tunturi.droid.todo.presentation.TodoListViewModel
import com.algalopez.tunturi.droid.todo.repository.ItemDao
import com.algalopez.tunturi.droid.todo.repository.TodoRepository
import com.algalopez.tunturi.droid.todo.repository.adapter.TodoMapper
import com.algalopez.tunturi.droid.todo.repository.adapter.TodoRepositoryAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.mapstruct.factory.Mappers


internal val instrumentedDependencyModuleList = module {

    single { TodoInMemoryDatabase.getInstance(get()) } bind ItemDao::class

    single { TodoRepository(get()) } bind TodoRepository::class
    single { Mappers.getMapper(TodoMapper::class.java) } bind TodoMapper::class

    single { TodoRepositoryAdapter(get(), get()) } bind ITodoRepository::class

    single { GetAllItemsActor(get()) }
    single { InsertItemActor(get()) }
    single { UpdateItemActor(get()) }
    single { RemoveItemActor(get()) }

    viewModel { TodoListViewModel(get(), get()) }
}
