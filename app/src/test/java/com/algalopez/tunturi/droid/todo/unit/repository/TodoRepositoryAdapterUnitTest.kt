package com.algalopez.tunturi.droid.todo.unit.repository

import com.algalopez.tunturi.droid.todo.repository.TodoRepository
import com.algalopez.tunturi.droid.todo.repository.TodoRepositoryAdapter
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TodoRepositoryAdapterUnitTest {

    private lateinit var adapter : TodoRepositoryAdapter

    @Mock
    private lateinit var todoRepository: TodoRepository

//    @Before
//    fun `init mocks`() {
//        adapter = EchoRepositoryAdapter(echoRepository = echoRepository)
//    }
//
//    @Test
//    fun `asd`() {
//
//    }
}