package com.masdika.hermanbakso.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.masdika.hermanbakso.R
import com.masdika.hermanbakso.databinding.ActivityRegisterBinding
import com.masdika.hermanbakso.model.UsersData

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    //viewBinding
    private lateinit var binding: ActivityRegisterBinding

    //FIREBASE AUTH
    private lateinit var auth: FirebaseAuth

    //FIREBASE REALTIMEDATABASE
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_register -> {
//                VARIABEL INPUT
                val email = binding.inputEmail.text.toString()
                val username = binding.inputUsername.text.toString()
                val noHp = binding.inputHp.text.toString()
                val password = binding.inputPassword.text.toString()
                val passwordValidation = binding.inputPassword2.text.toString()

                // VALIDASI
                if (email.isEmpty()) {
                    binding.inputEmail.error = "Email belum di isi!"
                    binding.inputEmail.requestFocus()
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.inputEmail.error = "Email tidak valid !"
                    binding.inputEmail.requestFocus()
                } else if (username.isEmpty()) {
                    binding.inputUsername.error = "Username belum di isi!"
                    binding.inputUsername.requestFocus()
                } else if (noHp.isEmpty()) {
                    binding.inputHp.error = "No HP belum di isi!"
                    binding.inputHp.requestFocus()
                } else if (!Patterns.PHONE.matcher(noHp).matches()) {
                    binding.inputHp.error = "No HP tidak valid!"
                    binding.inputHp.requestFocus()
                } else if (password.isEmpty()) {
                    binding.inputPassword.error = "Kata sandi belum di isi!"
                    binding.inputPassword.requestFocus()
                } else if (password.length < 8) {
                    binding.inputPassword.error = "Kata sandi harus berisi 8 karakter!"
                    binding.inputPassword.requestFocus()
                } else if (passwordValidation.isEmpty()) {
                    binding.inputPassword2.error = "Belum di isi!"
                    binding.inputPassword2.requestFocus()
                } else if (passwordValidation != password) {
                    binding.inputPassword2.error = "Kata sandi tidak sama!"
                    binding.inputPassword2.requestFocus()
                } else {

                    //INSERT DATA KE REALTIMEDATABASE FIREBASE
                    dbRef =
                        FirebaseDatabase.getInstance("https://herman-bakso-default-rtdb.asia-southeast1.firebasedatabase.app")
                            .getReference("UserRegisterData")
                    val usersData = UsersData(email, username, noHp)
                    dbRef.child(username).setValue(usersData).addOnSuccessListener {

                        //KOSONGKAN INPUT
                        binding.inputEmail.text!!.clear()
                        binding.inputUsername.text!!.clear()
                        binding.inputHp.text!!.clear()
                        binding.inputPassword.text!!.clear()
                        binding.inputPassword2.text!!.clear()

                        //REGISTRASI KE FIREBASE AUTH
                        registerFirebase(email, passwordValidation)
                    }.addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Gagal untuk menyimpan data : ${it.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

            R.id.btn_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }


    // FUNCTION REGISTER KE FIREBASE AUTH
    private fun registerFirebase(email: String, passwordValidation: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, passwordValidation)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Registrasi berhasil! silahkan login ke aplikasi",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(
                        this,
                        "Registrasi gagal : ${it.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}