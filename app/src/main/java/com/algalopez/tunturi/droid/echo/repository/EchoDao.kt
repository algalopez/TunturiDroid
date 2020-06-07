package com.algalopez.tunturi.droid.echo.repository

import androidx.room.*

@Dao
interface EchoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertMessage(echo: EchoMessage)

    @Update
    fun updateMessage(echo: EchoMessage)

    @Delete
    fun deleteMessage(echo: EchoMessage)

    @Query(value = "SELECT * FROM echo WHERE id == :name")
    fun getMessageById(name: String): List<EchoMessage>

    @Query(value = "SELECT * FROM echo")
    fun getMessages(): List<EchoMessage>
}
