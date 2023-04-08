package com.masdika.hermanbakso.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masdika.hermanbakso.R
import com.masdika.hermanbakso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    viewBinding
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}