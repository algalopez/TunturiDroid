package com.algalopez.tunturi.droid.echo.integration.repository

import com.algalopez.tunturi.droid.dependencyModuleList
import com.algalopez.tunturi.droid.echo.core.model.EchoMessage
import com.algalopez.tunturi.droid.echo.repository.EchoMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.junit.MockitoJUnitRunner
import java.time.Clock
import java.time.LocalDateTime

typealias RepositoryEchoMessage = com.algalopez.tunturi.droid.echo.repository.EchoMessage

@RunWith(MockitoJUnitRunner::class)
class EchoMapperIntegrationTest : AutoCloseKoinTest() {

    private companion object {
        private const val MESSAGE = "Example message"
        private val DATE_TIME = LocalDateTime.now(Clock.systemUTC())
    }

    private val echoMapper: EchoMapper by inject()

    @Before
    fun prepareDependencyInjection() {
        startKoin { modules(dependencyModuleList) }
    }

    @Test
    fun `map correct message to domain`() {
        val modelEchoMessage = EchoMessage(message = MESSAGE, dateTime = DATE_TIME)
        val repositoryEchoMessage = RepositoryEchoMessage(id = null, message = MESSAGE, dateTime = DATE_TIME)

        assertEquals(modelEchoMessage, echoMapper.toDomain(repositoryEchoMessage))
    }

    @Test
    fun `map correct message from domain`() {
        val modelEchoMessage = EchoMessage(message = MESSAGE, dateTime = DATE_TIME)
        val repositoryEchoMessage = RepositoryEchoMessage(id = null, message = MESSAGE, dateTime = DATE_TIME)

        assertEquals(repositoryEchoMessage, echoMapper.fromDomain(modelEchoMessage))
    }

    @Test
    fun `map null messages to and from domain`() {
        assertEquals(null, echoMapper.toDomain(null))
        assertEquals(null, echoMapper.fromDomain(null))
    }
}
