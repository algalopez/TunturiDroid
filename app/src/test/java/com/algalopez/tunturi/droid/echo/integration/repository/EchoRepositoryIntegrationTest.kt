package com.algalopez.tunturi.droid.echo.integration.repository

import com.algalopez.tunturi.droid.echo.integration.dependencyModuleList
import com.algalopez.tunturi.droid.echo.repository.EchoDao
import com.algalopez.tunturi.droid.echo.repository.EchoMessage
import com.algalopez.tunturi.droid.echo.repository.EchoRepository
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
import java.time.Clock
import java.time.LocalDateTime
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class EchoRepositoryIntegrationTest : AutoCloseKoinTest() {

    private val echoDaoStub: EchoDao by inject()
    private val echoRepository: EchoRepository by inject()

    @Before
    fun prepareDependencyInjection() {
        stopKoin()
        startKoin { modules(dependencyModuleList) }
    }

    @Test
    fun `get empty list of messages`() = runBlocking {
        given(echoDaoStub.getMessages()).willReturn(ArrayList<EchoMessage>())

        assertEquals(ArrayList<EchoMessage>(), echoRepository.getMessages())
    }

    @Test
    fun `get list of messages`() = runBlocking {
        val echoMessage1 = EchoMessage(message = "message1", dateTime = LocalDateTime.now(Clock.systemUTC()))
        val echoMessage2 = EchoMessage(message = "message2", dateTime = LocalDateTime.now(Clock.systemUTC()))

        given(echoDaoStub.getMessages()).willReturn(listOf(echoMessage1, echoMessage2))

        val echoMessages = echoRepository.getMessages()

        assertEquals(listOf(echoMessage1, echoMessage2), echoMessages)
    }

    @Test
    fun `insert message`() = runBlocking {
        val echoMessage = EchoMessage(message = "message", dateTime = LocalDateTime.now(Clock.systemUTC()))

        val unit = echoRepository.insertMessage(echoMessage)

        assertEquals(Unit, unit)
    }
}