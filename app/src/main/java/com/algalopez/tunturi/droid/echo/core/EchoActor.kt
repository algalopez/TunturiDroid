package com.algalopez.tunturi.droid.echo.core

import android.util.Log
import com.algalopez.tunturi.droid.common.BaseInteractor
import com.algalopez.tunturi.droid.echo.core.model.EchoMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Thread.sleep
import java.time.LocalDateTime

private const val TAG : String = "EchoActor"
private const val WAITING_TIME : Long = 1_000L

class EchoActor : BaseInteractor<String, EchoResponse>() {

    override suspend fun run(request: String): Flow<EchoResponse> = flow {
        Log.d(TAG, "Executing echo actor")
        sleep(WAITING_TIME)
        emit(EchoResponse.Loading(50))
        sleep(WAITING_TIME)
        emit(EchoResponse.Success(listOf(EchoMessage(LocalDateTime.now(), request))))
    }
}
