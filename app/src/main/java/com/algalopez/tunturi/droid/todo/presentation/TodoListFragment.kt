package com.algalopez.tunturi.droid.todo.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.algalopez.tunturi.droid.R
import com.algalopez.tunturi.droid.todo.core.TodoResponse
import com.algalopez.tunturi.droid.todo.core.model.Item
import kotlinx.android.synthetic.main.todo_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val TAG: String = "EchoFragment"

class TodoListFragment : Fragment() {

    private val todoListViewModel: TodoListViewModel by viewModel()
    private lateinit var rootView: View

    companion object {
        fun newInstance() = TodoListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.todo_list_fragment, container, false)

        todoListViewModel.getEchoResponse().observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is TodoResponse.Loading -> renderLoading(response.percentage)
                is TodoResponse.Error -> renderError(response.errorMessage)
                is TodoResponse.Success -> renderSuccess(response.itemList)
            }
        })

        val sendBtn = rootView.findViewById(R.id.send_btn) as Button
        sendBtn.setOnClickListener { onSendMessageClick() }

        return rootView
    }

    private fun onSendMessageClick() {

        Log.d(TAG, "Send button clicked")
        val message: String = request_tv.text.toString()
        todoListViewModel.sendMessage(message)
    }


    private fun renderLoading(percentage: Int) {

        Log.d(TAG, "Rendering loading: $percentage")
    }

    private fun renderError(errorMessage: String) {

        Log.d(TAG, "Rendering error: $errorMessage")
    }

    private fun renderSuccess(itemList: List<Item>) {

        Log.d(TAG, "Rendering success: $itemList")
    }

}
