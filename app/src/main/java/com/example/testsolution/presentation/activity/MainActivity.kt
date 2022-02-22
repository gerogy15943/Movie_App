package com.example.testsolution.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.testsolution.R

class MainActivity : AppCompatActivity() {

    var actionBarActivity: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBarActivity = supportActionBar

    }
}