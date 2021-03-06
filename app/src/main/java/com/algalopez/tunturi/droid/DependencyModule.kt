package com.algalopez.tunturi.droid

import androidx.room.Room
import com.algalopez.tunturi.droid.todo.core.actor.GetAllItemsActor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.actor.InsertItemActor
import com.algalopez.tunturi.droid.todo.core.actor.RemoveItemActor
import com.algalopez.tunturi.droid.todo.core.actor.UpdateItemActor
import com.algalopez.tunturi.droid.todo.presentation.TodoListViewModel
import com.algalopez.tunturi.droid.todo.repository.*
import com.algalopez.tunturi.droid.todo.repository.adapter.TodoMapper
import com.algalopez.tunturi.droid.todo.repository.adapter.TodoRepositoryAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.mapstruct.factory.Mappers

val dependencyModuleList = module {

    single { Room.databaseBuilder(get(), TodoDatabase::class.java, "tunturi").build() }
    single { get<TodoDatabase>().itemDao() } bind ItemDao::class

    single { Mappers.getMapper(TodoMapper::class.java) } bind TodoMapper::class
    single { TodoRepository(get()) }

    single { TodoRepositoryAdapter(get(), get()) } bind ITodoRepository::class

    single { GetAllItemsActor(get()) }
    single { InsertItemActor(get()) }
    single { UpdateItemActor(get()) }
    single { RemoveItemActor(get()) }

    viewModel { TodoListViewModel(get(), get()) }
}
