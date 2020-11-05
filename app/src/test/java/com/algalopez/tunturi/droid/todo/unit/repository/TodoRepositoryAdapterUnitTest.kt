package com.algalopez.tunturi.droid.todo.unit.repository

import com.algalopez.tunturi.droid.todo.repository.TodoMapper
import com.algalopez.tunturi.droid.todo.repository.TodoRepository
import com.algalopez.tunturi.droid.todo.repository.TodoRepositoryAdapter
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

private typealias DomainItem = com.algalopez.tunturi.droid.todo.core.model.Item
private typealias RepositoryItem = com.algalopez.tunturi.droid.todo.repository.Item


@ExtendWith(MockitoExtension::class)
class TodoRepositoryAdapterUnitTest {

    private companion object {
        private val DOMAIN_ITEM = DomainItem(id = 1, name = "name", color = "color")
        private val REPOSITORY_ITEM = RepositoryItem(id = 1, name = "name", color = "color")
        private const val ONCE = 1
    }

    private lateinit var adapter: TodoRepositoryAdapter

    @Mock
    private lateinit var todoRepository: TodoRepository

    @Mock
    private lateinit var todoMapper: TodoMapper

    @BeforeEach
    fun `init mocks`() {
        adapter = TodoRepositoryAdapter(todoRepository = todoRepository, todoMapper = todoMapper)
    }

    @Test
    fun `insert item`() = runBlocking {
        given(todoMapper.fromDomain(DOMAIN_ITEM)).willReturn(REPOSITORY_ITEM)

        adapter.insertItem(DOMAIN_ITEM)

        verify(todoRepository, times(ONCE)).insertItem(REPOSITORY_ITEM)
    }

    @Test
    fun `get all items`() = runBlocking {
        given(todoMapper.toDomain(REPOSITORY_ITEM)).willReturn(DOMAIN_ITEM)
        given(todoRepository.getAllItems()).willReturn(listOf(REPOSITORY_ITEM))

        val actualItems: List<DomainItem> = adapter.findAllItems()

        verify(todoRepository, times(ONCE)).getAllItems()
        assertEquals(listOf(DOMAIN_ITEM), actualItems)
    }

}
