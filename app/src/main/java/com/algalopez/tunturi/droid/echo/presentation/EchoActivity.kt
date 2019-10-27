package com.algalopez.tunturi.droid.echo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.algalopez.tunturi.droid.R

class EchoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.echo_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EchoFragment.newInstance())
                .commitNow()
        }
    }

}
