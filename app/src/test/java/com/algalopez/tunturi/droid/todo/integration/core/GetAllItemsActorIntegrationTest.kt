package com.algalopez.tunturi.droid.todo.integration.core

import com.algalopez.tunturi.droid.todo.core.TodoResponse
import com.algalopez.tunturi.droid.todo.core.actor.GetAllItemsActor
import com.algalopez.tunturi.droid.todo.core.exception.FakeException
import com.algalopez.tunturi.droid.todo.integration.integrationDependencyModuleList
import com.algalopez.tunturi.droid.todo.repository.ItemDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import com.algalopez.tunturi.droid.todo.core.model.Item as DomainItem
import com.algalopez.tunturi.droid.todo.repository.Item as RepositoryItem


@ExtendWith(MockitoExtension::class)
class GetAllItemsActorIntegrationTest : AutoCloseKoinTest() {

    private val itemDao: ItemDao by inject()
    private val getAllItemsActor: GetAllItemsActor by inject()

    @BeforeEach
    fun prepareDependencyInjection() {
        stopKoin()
        startKoin { modules(integrationDependencyModuleList) }
    }

    @AfterEach
    fun tearDownDependencyInjection() {
        stopKoin()
        Mockito.reset(itemDao)
    }

    @Test
    fun `should return a list of items`() = runBlocking {
        val item1 = RepositoryItem(id = 1, name = "name1", color = "color1")
        val item2 = RepositoryItem(id = 2, name = "name2", color = "color2")
        given(itemDao.getAllItems()).willReturn(arrayListOf(item1, item2))

        val response: TodoResponse.QuerySuccess = getFlowForQuerySuccess(getAllItemsActor.run(Unit))!!
        val itemList: List<DomainItem> = response.itemList

        assertNotNull(itemList)
        assertEquals(2, itemList.size)
        assertTrue(itemList.contains(DomainItem(1, "name1", "color1")))
        assertTrue(itemList.contains(DomainItem(2, "name2", "color2")))
    }

    @Test
    fun `should notify error for known exceptions`() = runBlocking {

        val expectedMessage = "Forced known exception"
        given(itemDao.getAllItems()).willThrow(FakeException(expectedMessage))

        val flow: Flow<TodoResponse> = getAllItemsActor.run(Unit)

        val error: TodoResponse.Error = getFlowForError(flow)!!
        assertEquals(expectedMessage, error.errorMessage)
    }

    @Test
    fun `should fail for unknown exceptions`() = runBlocking {

        val expectedMessage = "Forced unknown exception"
        given(itemDao.getAllItems()).willThrow(RuntimeException(expectedMessage))

        val flow: Flow<TodoResponse> = getAllItemsActor.run(Unit)

        var exceptionThrown: Throwable? = null
        flow
            .catch { e ->
                exceptionThrown = e
            }
            .collect { }

        assertNotNull(exceptionThrown)
        assertEquals(expectedMessage, exceptionThrown!!.message)
    }
}
