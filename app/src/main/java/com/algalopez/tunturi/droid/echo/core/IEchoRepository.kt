package com.algalopez.tunturi.droid.echo.core

import com.algalopez.tunturi.droid.echo.core.model.EchoMessage

interface IEchoRepository {

    fun storeMessage(echoMessage: EchoMessage): Long

    fun getMessageList(offset: Int, pageSize: Int): List<EchoMessage>
}
