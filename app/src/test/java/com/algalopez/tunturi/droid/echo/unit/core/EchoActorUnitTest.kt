package com.algalopez.tunturi.droid.echo.unit.core

import com.algalopez.tunturi.droid.echo.core.EchoActor
import com.algalopez.tunturi.droid.echo.core.EchoResponse
import com.algalopez.tunturi.droid.echo.core.IEchoRepository
import com.algalopez.tunturi.droid.echo.core.IEchoServer
import com.algalopez.tunturi.droid.echo.core.model.EchoMessage
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
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class EchoActorUnitTest {

    @Mock
    private lateinit var echoServer: IEchoServer

    @Mock
    private lateinit var echoRepository: IEchoRepository

    private lateinit var echoActor: EchoActor

    @Before
    fun `init mocks`() {

        echoActor = EchoActor(echoRepository = echoRepository, echoServer = echoServer)
    }

    @Test
    fun `test happy path`() = runBlocking {

        val exampleMessage = "Example message"
        val expectedMessage = EchoMessage(dateTime = LocalDateTime.now(), message = exampleMessage)

        Mockito.`when`(echoServer.send(exampleMessage)).thenReturn(exampleMessage) // whenever
        Mockito.`when`(echoRepository.storeMessage(any())).thenReturn(1L)
        Mockito.`when`(echoRepository.getMessageList(anyInt(), anyInt())).thenReturn(listOf(expectedMessage))

        val flow = echoActor.run(exampleMessage)
        flow.collect { value ->
            when (value) {
                is EchoResponse.Success ->  {
                    assertEquals(expectedMessage, value.echoMessageList[0])
                }
            }
        }
    }

    private fun <T> any(): T = Mockito.any<T>()
}
