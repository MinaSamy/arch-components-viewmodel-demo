package com.viewmodeldemo.minawissa.viewmodeldemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainFragment.OnSecondFragmentButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            return
        }

        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.container, mainFragment, "MainFragment")
                .commit()
    }

    override fun onSecondFragmentButtonClicked() {
        val secondFragment = SecondFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, secondFragment)
                .addToBackStack(null)
                .commit()
    }
}
