package com.algalopez.tunturi.droid.echo.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algalopez.tunturi.droid.echo.core.EchoActor
import com.algalopez.tunturi.droid.echo.core.EchoResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val TAG: String = "EchoViewModel"

class EchoViewModel(private val echoActor: EchoActor) : ViewModel() {

    private val echoResponse = MutableLiveData<EchoResponse>()

    fun getEchoResponse(): LiveData<EchoResponse> {

        Log.d(TAG, "getting livedata response")
        return echoResponse
    }

    fun sendMessage(message: String) {

        Log.d(TAG, "Sending something")

        viewModelScope.launch {
            val flow = echoActor.run(message)
            flow.collect { response ->
                Log.d(TAG, response.toString())
                echoResponse.value = response

            }
        }
    }

}
