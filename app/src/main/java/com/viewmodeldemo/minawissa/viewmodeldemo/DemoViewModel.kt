package com.viewmodeldemo.minawissa.viewmodeldemo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log

class DemoViewModel : ViewModel() {
    private val numbers = mutableListOf<Int>()

    private val _numbersLiveDate = MutableLiveData<List<Int>>()

    val numbersLiveData: LiveData<List<Int>>
        get() {
            if (_numbersLiveDate.value == null) {
                loadInitialData()
            }
            return _numbersLiveDate
        }

    private fun loadInitialData() {
        Log.e("AAA","Loading initial data")
        numbers.apply {
            add(1)
            add(2)
            add(3)
        }
        _numbersLiveDate.postValue(numbers)
    }

    fun addItem(item: Int) {
        numbers.add(item)
        _numbersLiveDate.postValue(numbers)
    }
}