package com.algalopez.tunturi.droid.todo.unit.core

import com.algalopez.tunturi.droid.todo.core.actor.GetAllItemsActor
import com.algalopez.tunturi.droid.todo.core.ITodoRepository
import com.algalopez.tunturi.droid.todo.core.TodoQueryResponse
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
class GetAllItemsActorUnitTest {

    @Mock
    private lateinit var todoRepository: ITodoRepository

    private lateinit var getAllItemsActor: GetAllItemsActor

    @BeforeEach
    fun `init mocks`() {

        getAllItemsActor = GetAllItemsActor(todoRepository = todoRepository)
    }

    @Test
    fun `test find all items`() = runBlocking {

        val expectedItem = Item(id = 1, name = "name", color = "color")

        Mockito.`when`(todoRepository.findAllItems()).thenReturn(listOf(expectedItem))

        val flow = getAllItemsActor.run(Unit)
        flow.collect { value ->
            when (value) {
                is TodoQueryResponse.Success -> {
                    assertEquals(expectedItem, value.itemList[0])
                }
            }
        }
    }

}
