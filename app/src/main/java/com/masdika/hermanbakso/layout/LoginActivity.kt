package com.masdika.hermanbakso.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masdika.hermanbakso.R
import com.masdika.hermanbakso.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // viewBinding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}