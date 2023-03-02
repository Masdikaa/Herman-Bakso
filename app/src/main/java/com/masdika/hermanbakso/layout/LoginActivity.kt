package com.masdika.hermanbakso.layout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.masdika.hermanbakso.R
import com.masdika.hermanbakso.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    // viewBinding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener (this)

    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_login -> {
                val username = binding.inputUsername.text.toString()
                val password = binding.inputPassword.text.toString()

                //Validasi
                if(username.isEmpty()){
                    binding.inputUsername.error = "Username belum di isi"
                    binding.inputUsername.requestFocus()
                }else if(password.isEmpty()){
                    binding.inputPassword.error = "Password belum di isi"
                    binding.inputPassword.requestFocus()
                }else if(password.length < 8){
                    binding.inputPassword.error = "Password harus lebih dari 8"
                    binding.inputPassword.requestFocus()
                } else{
                    Toast.makeText(this, "Validasi Done", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btn_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
        }

    }
}