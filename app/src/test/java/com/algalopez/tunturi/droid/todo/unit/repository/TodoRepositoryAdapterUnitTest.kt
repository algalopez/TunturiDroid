package com.algalopez.tunturi.droid.todo.unit.repository

import com.algalopez.tunturi.droid.todo.repository.TodoMapper
import com.algalopez.tunturi.droid.todo.repository.TodoRepository
import com.algalopez.tunturi.droid.todo.repository.TodoRepositoryAdapter
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TodoRepositoryAdapterUnitTest {

    private lateinit var adapter : TodoRepositoryAdapter

    @Mock
    private lateinit var todoRepository: TodoRepository
    @Mock
    private lateinit var todoMapper: TodoMapper

    @BeforeEach
    fun `init mocks`() {
        adapter = TodoRepositoryAdapter(todoRepository = todoRepository, todoMapper = todoMapper)
    }

    @Test
    fun `asd asd`() {

        assertTrue(true)
    }
}