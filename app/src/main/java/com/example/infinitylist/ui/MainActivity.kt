package com.example.infinitylist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.infinitylist.R
import com.example.infinitylist.ui.PostList.PostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, PostFragment())
                .commit()
    }
}