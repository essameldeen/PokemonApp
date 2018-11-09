package com.task.toshiba.pekemonapp.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.task.toshiba.pekemonapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolBar.title = "Pokemon List"
        setSupportActionBar(toolBar)
    }
}
