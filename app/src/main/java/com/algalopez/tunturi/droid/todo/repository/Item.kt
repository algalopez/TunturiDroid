package com.algalopez.tunturi.droid.todo.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "todo_item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    @ColumnInfo(name = "color")
    val color: String
)
