package com.algalopez.tunturi.droid.echo.repository

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EchoRepository(private val echoDao: EchoDao) {

    @WorkerThread
    suspend fun insertMessage(echoMessage: EchoMessage) = withContext(Dispatchers.IO) {
//        echoDao.insertMessage(echoMessage)
        Log.d(this@EchoRepository.toString(), "insertMessage: $echoMessage")
//      TODO("not implemented")
    }

    @WorkerThread
    suspend fun getMessages() = withContext(Dispatchers.IO) {
//        echoDao.getMessages()
        Log.d(this@EchoRepository.toString(), "getMessages")
//        TODO("not implemented")

    }
}