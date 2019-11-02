package com.algalopez.tunturi.droid.echo.core.model

import java.time.LocalDateTime

data class EchoMessage(
    val dateTime: LocalDateTime,
    val message: String
)
