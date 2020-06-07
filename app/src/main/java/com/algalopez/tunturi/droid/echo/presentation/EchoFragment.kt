package com.algalopez.tunturi.droid.echo.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.algalopez.tunturi.droid.R
import com.algalopez.tunturi.droid.echo.core.EchoResponse
import com.algalopez.tunturi.droid.echo.core.model.EchoMessage
import kotlinx.android.synthetic.main.echo_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val TAG: String = "EchoFragment"

class EchoFragment : Fragment() {

    private val echoViewModel: EchoViewModel by viewModel()
    private lateinit var rootView: View

    companion object {
        fun newInstance() = EchoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.echo_fragment, container, false)

        echoViewModel.getEchoResponse().observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is EchoResponse.Loading -> renderLoading(response.percentage)
                is EchoResponse.Error -> renderError(response.errorMessage)
                is EchoResponse.Success -> renderSuccess(response.echoMessageList)
            }
        })

        val sendBtn = rootView.findViewById(R.id.send_btn) as Button
        sendBtn.setOnClickListener { onSendMessageClick() }

        return rootView
    }

    private fun onSendMessageClick() {

        Log.d(TAG, "Send button clicked")
        val message: String = request_tv.text.toString()
        echoViewModel.sendMessage(message)
    }


    private fun renderLoading(percentage: Int) {

        Log.d(TAG, "Rendering loading: $percentage")
    }

    private fun renderError(errorMessage: String) {

        Log.d(TAG, "Rendering error: $errorMessage")
    }

    private fun renderSuccess(echoMessageList: List<EchoMessage>) {

        Log.d(TAG, "Rendering success: $echoMessageList")
    }

}
