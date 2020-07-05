package com.algalopez.tunturi.droid.echo.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EchoRepository(private val echoDao: EchoDao) {

    suspend fun insertMessage(echoMessage: EchoMessage) = withContext(Dispatchers.IO) {
        Log.d(this@EchoRepository.toString(), "Inserting message: $echoMessage")
        echoDao.insertMessage(echoMessage)
    }

    suspend fun getMessages(): List<EchoMessage> = withContext(Dispatchers.IO) {
        Log.d(this@EchoRepository.toString(), "Getting all messages")
        return@withContext echoDao.getMessages()
    }
}
