package com.algalopez.tunturi.droid.echo.presentation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.algalopez.tunturi.droid.R

class EchoFragment : Fragment() {

    companion object {
        fun newInstance() = EchoFragment()
    }

    private lateinit var viewModel: EchoViewModel
//    val viewModel: EchoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.echo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EchoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
