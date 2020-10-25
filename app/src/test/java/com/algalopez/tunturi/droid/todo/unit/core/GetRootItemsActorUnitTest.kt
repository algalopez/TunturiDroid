package com.algalopez.tunturi.droid.todo.unit.core

import com.algalopez.tunturi.droid.todo.core.GetRootItemsActor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.TodoResponse
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRootItemsActorUnitTest {

    @Mock
    private lateinit var todoRepository: ITodoRepository

    private lateinit var getRootItemsActor: GetRootItemsActor

    @Before
    fun `init mocks`() {

        getRootItemsActor = GetRootItemsActor(todoRepository = todoRepository)
    }

    @Test
    fun `test find all items`() = runBlocking {

        val expectedItem = Item(id = 1, name = "name", color = "color")

        Mockito.`when`(todoRepository.findAllItems(anyInt(), anyInt())).thenReturn(listOf(expectedItem))

        val flow = getRootItemsActor.run("exampleMessage")
        flow.collect { value ->
            when (value) {
                is TodoResponse.Success ->  {
                    assertEquals(expectedItem, value.itemList[0])
                }
            }
        }
    }

    private fun <T> any(): T = Mockito.any<T>()
}
