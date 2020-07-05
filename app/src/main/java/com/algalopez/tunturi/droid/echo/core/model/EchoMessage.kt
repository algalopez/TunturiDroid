package com.algalopez.tunturi.droid.echo.core.model

import java.time.LocalDateTime

data class EchoMessage(
    val message: String,
    val dateTime: LocalDateTime
)
