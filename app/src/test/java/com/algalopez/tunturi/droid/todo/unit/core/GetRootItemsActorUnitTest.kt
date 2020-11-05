package com.algalopez.tunturi.droid.todo.unit.core

import com.algalopez.tunturi.droid.todo.core.GetRootItemsActor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.TodoResponse
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class GetRootItemsActorUnitTest {

    @Mock
    private lateinit var todoRepository: ITodoRepository

    private lateinit var getRootItemsActor: GetRootItemsActor

    @BeforeEach
    fun `init mocks`() {

        getRootItemsActor = GetRootItemsActor(todoRepository = todoRepository)
    }

    @Test
    fun `test find all items`() = runBlocking {

        val expectedItem = Item(id = 1, name = "name", color = "color")

        Mockito.`when`(todoRepository.findAllItems()).thenReturn(listOf(expectedItem))

        val flow = getRootItemsActor.run("exampleMessage")
        flow.collect { value ->
            when (value) {
                is TodoResponse.Success -> {
                    assertEquals(expectedItem, value.itemList[0])
                }
            }
        }
    }

}
