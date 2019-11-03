package com.algalopez.tunturi.droid.echo.repository

import com.algalopez.tunturi.droid.echo.core.IEchoRepository
import com.algalopez.tunturi.droid.echo.core.model.EchoMessage

class EchoRepositoryAdapter: IEchoRepository {

    override fun storeMessage(echoMessage: EchoMessage): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMessageList(offset: Int, pageSize: Int): List<EchoMessage> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}