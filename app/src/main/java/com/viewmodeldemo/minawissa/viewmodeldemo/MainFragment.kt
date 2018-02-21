package com.viewmodeldemo.minawissa.viewmodeldemo


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainFragment : Fragment() {

    /*private val viewModel: DemoViewModel by lazy {
        ViewModelProviders.of(this).get(DemoViewModel::class.java)
    }*/

    private lateinit var viewModel: DemoViewModel

    var buttonListener: OnSecondFragmentButtonClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(DemoViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        try {
            buttonListener = activity as OnSecondFragmentButtonClickListener
        } catch (exception: ClassCastException) {
            throw IllegalStateException("Activity must implement OnSecondFragmentButtonClickListener interface")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textViewNumbers = view.findViewById<TextView>(R.id.text_view_numbers)

        viewModel.numbersLiveData.observe(this, object : Observer<List<Int>> {
            override fun onChanged(numbers: List<Int>?) {
                Log.e("AAA", numbers?.toString())
                textViewNumbers.text = numbers?.toString()
            }
        })

        val newItemEditText = view.findViewById<EditText>(R.id.edit_text_new_item)

        val addButton = view.findViewById<Button>(R.id.button_add)
        addButton.setOnClickListener({ viewModel.addItem(newItemEditText.text.toString().toInt()) })

        val secondFragmentButton = view.findViewById<Button>(R.id.button_show_second_fragment)
        secondFragmentButton.setOnClickListener({ buttonListener?.onSecondFragmentButtonClicked() })
    }


    interface OnSecondFragmentButtonClickListener {
        fun onSecondFragmentButtonClicked()
    }

}
