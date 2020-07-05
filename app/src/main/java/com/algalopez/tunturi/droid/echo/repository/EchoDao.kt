package com.algalopez.tunturi.droid.echo.repository

import androidx.room.*

@Dao
interface EchoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMessage(vararg echo: EchoMessage)

    @Update
    suspend fun updateMessage(vararg echo: EchoMessage)

    @Delete
    suspend fun deleteMessage(vararg echo: EchoMessage)

    @Query(value = "SELECT * FROM echo WHERE id == :name")
    suspend fun getMessageById(name: String): List<EchoMessage>

    @Query(value = "SELECT * FROM echo")
    suspend fun getMessages(): List<EchoMessage>
}
