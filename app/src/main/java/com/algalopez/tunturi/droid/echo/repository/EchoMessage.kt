package com.algalopez.tunturi.droid.echo.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "echo")
data class EchoMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val message: String,
    @ColumnInfo(name = "date_time")
    val dateTime: LocalDateTime
)
