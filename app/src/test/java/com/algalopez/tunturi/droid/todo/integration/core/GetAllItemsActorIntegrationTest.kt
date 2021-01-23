package com.algalopez.tunturi.droid.todo.integration.core

import com.algalopez.tunturi.droid.todo.core.TodoResponse
import com.algalopez.tunturi.droid.todo.core.actor.GetAllItemsActor
import com.algalopez.tunturi.droid.todo.integration.integrationDependencyModuleList
import com.algalopez.tunturi.droid.todo.repository.ItemDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.BDDMockito.given
import org.mockito.junit.jupiter.MockitoExtension

private typealias DomainItem = com.algalopez.tunturi.droid.todo.core.model.Item
private typealias RepositoryItem = com.algalopez.tunturi.droid.todo.repository.Item

@ExtendWith(MockitoExtension::class)
class GetAllItemsActorIntegrationTest : AutoCloseKoinTest() {

    private val itemDao: ItemDao by inject()
    private val getAllItemsActor: GetAllItemsActor by inject()

    @BeforeEach
    fun prepareDependencyInjection() {
        stopKoin()
        startKoin { modules(integrationDependencyModuleList) }
    }

    @Test
    fun `should return a list of items`() = runBlocking {
        val item1 = RepositoryItem(id = 1, name = "name1", color = "color1")
        val item2 = RepositoryItem(id = 2, name = "name2", color = "color2")
        given(itemDao.getAllItems()).willReturn(arrayListOf(item1, item2))

        val itemList: List<DomainItem> = getFlowUniqueLastElement(getAllItemsActor.run(Unit))

        assertEquals(2, itemList.size)
        assertTrue(itemList.contains(DomainItem(1, "name1", "color1")))
        assertTrue(itemList.contains(DomainItem(2, "name2", "color2")))
    }

    private suspend fun getFlowUniqueLastElement(flow: Flow<TodoResponse>): List<DomainItem> {
        var itemList: List<DomainItem>? = null
        flow.collectLatest { element ->
            when (element) {
                is TodoResponse.Loading -> {
                }
                is TodoResponse.Error -> fail()
                is TodoResponse.CommandSuccess -> fail()
                is TodoResponse.QuerySuccess -> {
                    if (itemList != null) {
                        fail()
                    } else {
                        itemList = element.itemList
                    }
                }
            }
        }
        assertNotNull(itemList)
        return itemList!!
    }
}
