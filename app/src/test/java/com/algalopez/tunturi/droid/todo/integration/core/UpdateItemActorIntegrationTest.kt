package com.algalopez.tunturi.droid.todo.integration.core

import com.algalopez.tunturi.droid.todo.core.TodoResponse
import com.algalopez.tunturi.droid.todo.core.actor.UpdateItemActor
import com.algalopez.tunturi.droid.todo.integration.integrationDependencyModuleList
import com.algalopez.tunturi.droid.todo.repository.ItemDao
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
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
class UpdateItemActorIntegrationTest : AutoCloseKoinTest() {

    private val itemDao: ItemDao by inject()
    private val updateItemsActor: UpdateItemActor by inject()

    @BeforeEach
    fun prepareDependencyInjection() {
        stopKoin()
        startKoin { modules(integrationDependencyModuleList) }
    }

    @Test
    fun `should return a list of items`() = runBlocking {
        val repositoryItem = RepositoryItem(id = 1, name = "name1", color = "color1")
        given(itemDao.updateItem(repositoryItem)).willReturn(Unit)

        val domainItem = DomainItem(id = 1, name = "name1", color = "color1")
        val response: TodoResponse.CommandSuccess? = getFlowForCommandSuccess(updateItemsActor.run(domainItem))

        assertNotNull(response)
        Mockito.verify(itemDao).updateItem(repositoryItem)
    }

}
