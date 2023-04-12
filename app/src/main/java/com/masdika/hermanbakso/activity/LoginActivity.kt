package com.masdika.hermanbakso.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.masdika.hermanbakso.R
import com.masdika.hermanbakso.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    // viewBinding
    private lateinit var binding: ActivityLoginBinding

    // Firebase auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Change status bar color into white
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        binding.btnRegister.setOnClickListener(this)
        //binding.btnLogin.setOnClickListener(this)
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_login -> {
                val email = binding.inputEmail.text.toString()
                val password = binding.inputPassword.text.toString()

                //Validasi
                if (email.isEmpty()) {
                    binding.inputEmail.error = "Email belum di isi"
                    binding.inputEmail.requestFocus()
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.inputEmail.error = "Email tidak valid"
                    binding.inputEmail.requestFocus()
                } else if (password.isEmpty()) {
                    binding.inputPassword.error = "Password belum di isi"
                    binding.inputPassword.requestFocus()
                } else if (password.length < 8) {
                    binding.inputPassword.error = "Password harus lebih dari 8"
                    binding.inputPassword.requestFocus()
                } else {
                    //Toast.makeText(this, "Validasi Done", Toast.LENGTH_SHORT).show()
                    loginFirebase(email, password)
                }
            }

            R.id.btn_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
        }
    }

    private fun loginFirebase(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Selamat Datang", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        this,
                        "Gagal masuk ke aplikasi : ${it.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}