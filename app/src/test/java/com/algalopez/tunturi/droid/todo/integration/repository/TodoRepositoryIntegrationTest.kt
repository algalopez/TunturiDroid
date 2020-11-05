package com.algalopez.tunturi.droid.todo.integration.repository

import com.algalopez.tunturi.droid.todo.integration.dependencyModuleList
import com.algalopez.tunturi.droid.todo.repository.Item
import com.algalopez.tunturi.droid.todo.repository.ItemDao
import com.algalopez.tunturi.droid.todo.repository.TodoRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.BDDMockito.given
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class TodoRepositoryIntegrationTest : AutoCloseKoinTest() {

    private val itemDaoStub: ItemDao by inject()
    private val todoRepository: TodoRepository by inject()

    @BeforeEach
    fun prepareDependencyInjection() {
        stopKoin()
        startKoin { modules(dependencyModuleList) }
    }

    @Test
    fun `get empty list of items`() = runBlocking {
        given(itemDaoStub.getAllItems()).willReturn(emptyList())

        assertEquals(ArrayList<Item>(), todoRepository.getAllItems())
    }

    @Test
    fun `get all items`() = runBlocking {
        val item1 = Item(id = 1, name = "name1", color = "color1")
        val item2 = Item(id = 2, name = "name2", color = "color2")
        given(itemDaoStub.getAllItems()).willReturn(listOf(item1, item2))

        val actualItems = todoRepository.getAllItems()

        assertEquals(listOf(item1, item2), actualItems)
    }

    @Test
    fun `get item by id`() = runBlocking {
        val item1 = Item(id = 1, name = "name1", color = "color1")
        given(itemDaoStub.getItemById(item1.id!!)).willReturn(item1)

        val actualItem = todoRepository.getItemById(item1.id!!)

        assertEquals(item1, actualItem)
    }

    @Test
    fun `insert item`() = runBlocking {
        val echoMessage = Item(id = 1, name = "name", color = "color")

        val unit = todoRepository.insertItem(echoMessage)

        assertEquals(Unit, unit)
    }
}