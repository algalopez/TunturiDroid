package com.algalopez.tunturi.droid.echo.core

import android.util.Log
import com.algalopez.tunturi.droid.common.BaseInteractor
import com.algalopez.tunturi.droid.echo.core.model.EchoMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime

class EchoActor(
    private val echoServer: IEchoServer,
    private val echoRepository: IEchoRepository
) : BaseInteractor<String, EchoResponse>() {

    /**
     * Send a message to the echo server, and store the response and datetime
     *
     * @param request message to be sent to the echo server
     * @return list of last n stored responses
     */
    override suspend fun run(request: String): Flow<EchoResponse> = flow {

        Log.d(this@EchoActor.toString(), "Executing echo actor")

        val response: String = echoServer.send(request)
        val now: LocalDateTime = LocalDateTime.now()
        emit(EchoResponse.Loading(33))

        echoRepository.storeMessage(EchoMessage(message = response, dateTime = now))
        emit(EchoResponse.Loading(66))

        val echoMessageList: List<EchoMessage> = echoRepository.getMessageList(0, 10)

        emit(EchoResponse.Success(echoMessageList))
    }
}
