package com.algalopez.tunturi.droid.todo.repository

import androidx.room.*

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertItem(vararg item: Item)

    @Update
    suspend fun updateItem(vararg item: Item)

    @Delete
    suspend fun deleteItem(vararg item: Item)

    @Query(value = "SELECT * FROM todo_item WHERE id == :id")
    suspend fun getItemById(id: Int): Item

    @Query(value = "SELECT * FROM todo_item")
    suspend fun getAllItems(): List<Item>
}
