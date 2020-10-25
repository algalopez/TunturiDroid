package com.algalopez.tunturi.droid.todo.integration.repository

import com.algalopez.tunturi.droid.todo.integration.dependencyModuleList
import com.algalopez.tunturi.droid.todo.repository.Item
import com.algalopez.tunturi.droid.todo.repository.ItemDao
import com.algalopez.tunturi.droid.todo.repository.TodoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.BDDMockito.given
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class TodoRepositoryIntegrationTest : AutoCloseKoinTest() {

    private val itemDaoStub: ItemDao by inject()
    private val todoRepository: TodoRepository by inject()

    @Before
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
    fun `get list of messages`() = runBlocking {
        val item1 = Item(id = 1, name = "name1", color = "color1")
        val item2 = Item(id = 2, name = "name2", color = "color2")
        given(itemDaoStub.getAllItems()).willReturn(listOf(item1, item2))

        val echoMessages = todoRepository.getAllItems()

        assertEquals(listOf(item1, item2), echoMessages)
    }

    @Test
    fun `insert message`() = runBlocking {
        val echoMessage = Item(id = 1, name = "name", color = "color")

        val unit = todoRepository.insertItem(echoMessage)

        assertEquals(Unit, unit)
    }
}