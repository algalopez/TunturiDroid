package com.algalopez.tunturi.droid.echo.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [EchoMessage::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverters::class)
abstract class EchoDatabase : RoomDatabase() {

    abstract fun echoDao(): EchoDao;

    companion object {
        private const val databaseName = "tunturi"
        private var dbInstance: EchoDao? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we did not alter the table, there’s nothing else to do here.
            }
        }

        fun getInstance(context: Context): EchoDao {
            if (dbInstance == null) {
                Room.databaseBuilder(context, EchoDatabase::class.java, databaseName)
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also {
                        dbInstance = it.echoDao()
                    }
            }
            return dbInstance!!
        }

    }

}
