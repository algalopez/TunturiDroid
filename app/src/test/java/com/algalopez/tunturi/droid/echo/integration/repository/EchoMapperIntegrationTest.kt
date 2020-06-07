package com.algalopez.tunturi.droid.echo.integration.repository

import android.util.Log
import com.algalopez.tunturi.droid.dependencyModuleList
import com.algalopez.tunturi.droid.echo.repository.EchoMapper
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class EchoMapperIntegrationTest : KoinTest {

    private val echoMapper: EchoMapper by inject()

    @Before
    fun prepareDependencyInjection() {
        startKoin { modules(dependencyModuleList) }
    }

    @Test
    fun modelToDataTest() {

        Log.d(this@EchoMapperIntegrationTest.toString(), "asdasd")
        echoMapper.dataToModel(null)
    }
}
