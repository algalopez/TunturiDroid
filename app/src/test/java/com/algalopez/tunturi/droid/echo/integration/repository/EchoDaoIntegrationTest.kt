package com.algalopez.tunturi.droid.echo.integration.repository

import com.algalopez.tunturi.droid.echo.integration.dependencyModuleList
import com.algalopez.tunturi.droid.echo.repository.EchoDao
import com.algalopez.tunturi.droid.echo.repository.EchoMessage
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import java.time.Clock
import java.time.LocalDateTime

@RunWith(RobolectricTestRunner::class)
class EchoDaoIntegrationTest : AutoCloseKoinTest() {

    private val echoDao: EchoDao by inject()

    @Before
    fun prepareDependencyInjection() {
        stopKoin()
        startKoin { modules(dependencyModuleList) }
    }

    @Test
    fun `in memory dao test to remove soon`() = runBlocking() {
        val now = LocalDateTime.now(Clock.systemUTC())
        val message1 = "Sample 1"
        val message2 = "Sample 2"

        var messages: List<EchoMessage> = echoDao.getMessages()
        assertThat(messages.size, `is`(0))

        echoDao.insertMessage(EchoMessage(message = message1, dateTime = now))
        messages = echoDao.getMessages()
        assertThat(messages.size, `is`(1))

        val id = messages[0].id
        val echoMessage = echoDao.getMessageById(id!!)
        assertThat(echoMessage.size, `is`(1))
        assertThat(echoMessage[0].message, `is`(message1))

        echoDao.insertMessage(EchoMessage(message = message2, dateTime = now))
        messages = echoDao.getMessages()
        assertThat(messages.size, `is`(2))

    }
}