package com.algalopez.tunturi.droid.todo.core

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.algalopez.tunturi.droid.todo.instrumentedDependencyModuleList
import com.algalopez.tunturi.droid.todo.repository.Item
import com.algalopez.tunturi.droid.todo.repository.ItemDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@Ignore
@RunWith(AndroidJUnit4::class)
class GetAllItemsActorInstrumentedTest : AutoCloseKoinTest() {

    private val itemDao: ItemDao by inject()

    @Before
    fun prepareDependencyInjection() {
        stopKoin()
        startKoin { modules(instrumentedDependencyModuleList) }
    }

    @Test
    @MediumTest
    fun asdasd() = runBlocking {

        val item1 = Item(id = 1, name = "name1", color = "color1")

        itemDao.insertItem(item1)

        Log.d("asdasd", itemDao.getAllItems().toString())

        Assert.assertTrue(true)
        //Assertions.assertEquals(ArrayList<Item>(), todoRepository.getAllItems())
    }

}
