package com.algalopez.tunturi.droid.echo.core

import android.util.Log
import com.algalopez.tunturi.droid.common.BaseInteractor
import java.lang.Thread.sleep

private const val TAG : String = "EchoActor"
private const val WAITING_TIME : Long = 1000L

class EchoActor : BaseInteractor<String, String>() {


    override fun run(request: String): String {
        Log.d(TAG, "Executing echo actor")
        sleep(WAITING_TIME)
        return "Message $request"
    }
}
