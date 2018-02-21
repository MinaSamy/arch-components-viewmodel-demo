package com.viewmodeldemo.minawissa.viewmodeldemo


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class SecondFragment : Fragment() {

    private lateinit var viewModel: DemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(DemoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textViewNumbers = view.findViewById<TextView>(R.id.text_view_numbers)

        viewModel.numbersLiveData.observe(this, object : Observer<List<Int>> {
            override fun onChanged(numbers: List<Int>?) {
                textViewNumbers.text = numbers?.toString()
            }
        })

        val addButton = view.findViewById<Button>(R.id.button_add_new_item)
        addButton.setOnClickListener { viewModel.addItem(100) }
    }

}
