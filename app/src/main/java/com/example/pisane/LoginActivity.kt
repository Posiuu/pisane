package com.example.pisane

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.controler.InputValidation
import com.example.pisane.databinding.ActivityLoginBinding
import com.example.pisane.model.User
import com.example.pisane.sql.DatabaseHelper

class LoginActivity : AppCompatActivity() {

    private val activity = this@LoginActivity
    private lateinit var binding: ActivityLoginBinding
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(activity)
        inputValidation = InputValidation(activity)

        //addUsers()

        binding.btnLogin.alpha = 0f;
        binding.btnLogin.animate().alpha(1f).duration = 1500;

        binding.btnLogin.setOnClickListener {
            binding.btnLogin.alpha = 0f;
            binding.btnLogin.animate().alpha(1f).duration = 1500;
            verifyFromSQLite()
        }
    }

    private fun verifyFromSQLite() {
        if (inputValidation.isInputEditTextFilled(binding.etUsername) &&
                inputValidation.isInputEditTextEmail(binding.etUsername) &&
                inputValidation.isInputEditTextFilled(binding.etPassword) &&
                databaseHelper.checkUser(binding.etUsername.text.toString().trim { it <= ' ' }, binding.etPassword.text.toString().trim { it <= ' ' })) {
            val accountsIntent = Intent(activity, MainMenuActivity::class.java)
            emptyInputEditText()
            startActivity(accountsIntent)
        }
        else {
            Toast.makeText(this,
                    "Nieprawidłowy login lub hasło.", Toast.LENGTH_LONG).show()
        }
    }
    private fun emptyInputEditText() {
        binding.etUsername.text = null
        binding.etPassword.text = null
    }
    private fun addUsers() {
        val user1 = User(name = "Jan", email = "1@email.com", password = "pass1")
        val user2 = User(name = "Jakub", email = "2@email.com", password = "pass2")
        val user3 = User(name = "Jerzy", email = "3@email.com", password = "pass3")

        databaseHelper.addUser(user1)
        databaseHelper.addUser(user2)
        databaseHelper.addUser(user3)
    }
}