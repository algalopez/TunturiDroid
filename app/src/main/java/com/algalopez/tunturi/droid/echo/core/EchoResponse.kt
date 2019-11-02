package com.algalopez.tunturi.droid.echo.core

import com.algalopez.tunturi.droid.echo.core.model.EchoMessage

sealed class EchoResponse {

    data class Error(val errorMessage: String) : EchoResponse()

    data class Success(val echoMessageList: List<EchoMessage>) : EchoResponse()

    data class Loading(val percentage: Int) : EchoResponse()
}