package com.algalopez.tunturi.droid.todo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.algalopez.tunturi.droid.R

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TodoListFragment.newInstance())
                .commitNow()
        }
    }

}
