package com.algalopez.tunturi.droid.todo.integration.repository

import com.algalopez.tunturi.droid.dependencyModuleList
import com.algalopez.tunturi.droid.todo.core.model.Item
import com.algalopez.tunturi.droid.todo.repository.TodoMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.junit.jupiter.MockitoExtension

private typealias DomainItem = Item
private typealias RepositoryItem = com.algalopez.tunturi.droid.todo.repository.Item

@ExtendWith(MockitoExtension::class)
class TodoMapperIntegrationTest : AutoCloseKoinTest() {

    private companion object {
        private const val MESSAGE = "Example message"
        private const val COLOR = "Example color"
    }

    private val todoMapper: TodoMapper by inject()

    @BeforeEach
    fun prepareDependencyInjection() {
        startKoin { modules(dependencyModuleList) }
    }

    @Test
    fun `map correct message to domain`() {
        val expectedItem = DomainItem(id = 1, name = MESSAGE, color = COLOR)
        val repositoryItem = RepositoryItem(id = 1, name = MESSAGE, color = COLOR)

        assertEquals(expectedItem, todoMapper.toDomain(repositoryItem))
    }

    @Test
    fun `map correct message from domain`() {
        val expectedItem = RepositoryItem(id = 1, name = MESSAGE, color = COLOR)
        val domainItem = DomainItem(id = 1, name = MESSAGE, color = COLOR)

        assertEquals(expectedItem, todoMapper.fromDomain(domainItem))
    }

    @Test
    fun `map null messages to and from domain`() {
        assertEquals(null, todoMapper.toDomain(null))
        assertEquals(null, todoMapper.fromDomain(null))
    }
}
