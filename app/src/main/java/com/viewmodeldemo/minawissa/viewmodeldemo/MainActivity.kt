package com.viewmodeldemo.minawissa.viewmodeldemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewNumbers = findViewById<TextView>(R.id.text_view_numbers)

        val viewModel = ViewModelProviders.of(this).get(DemoViewModel::class.java)
        viewModel.numbersLiveData.observe(this, object : Observer<List<Int>> {
            override fun onChanged(numbers: List<Int>?) {
                Log.e("AAA", numbers?.toString())
                textViewNumbers.text = numbers?.toString()
            }

        })

        val newItemEditText = findViewById<EditText>(R.id.edit_text_new_item)

        val addButton = findViewById<Button>(R.id.button_add)
        addButton.setOnClickListener({ viewModel.addItem(newItemEditText.text.toString().toInt()) })
    }
}
