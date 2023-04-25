package com.sk.patientsignupform.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sk.patientsignupform.MainActivity
import com.sk.patientsignupform.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setOnClickListeners()
    }

    /***
     * Function to set on click listeners
     */
    private fun setOnClickListeners() {
        binding.btnLogIn.setOnClickListener {
            validatePhoneNumber()
        }
    }

    /***
     * Function to validate phone number
     */
    private fun validatePhoneNumber() {
        val phone: String;
        if (binding.etPhoneNumber.text.toString().isNotEmpty()) {
            phone = binding.etPhoneNumber.text.toString().trim()
            if (android.util.Patterns.PHONE.matcher(phone).matches() && phone.length == 10) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "NO MATCH", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Please enter mobile number ", Toast.LENGTH_LONG)
                .show()
        }
    }
}