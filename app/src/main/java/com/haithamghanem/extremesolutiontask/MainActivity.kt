package com.haithamghanem.extremesolutiontask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.haithamghanem.extremesolutiontask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindin: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindin = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindin.root)

    }
}