package com.pisane.pisane.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.controler.shared_preferences.PREF_USERNAME
import com.pisane.pisane.controler.shared_preferences.SharedPreferencesManager
import com.pisane.pisane.databinding.ActivityLoginBinding
import com.pisane.pisane.controler.login.LoginManager
import com.pisane.pisane.controler.shared_preferences.PREF_USER_ID
import com.pisane.pisane.model.User

class LoginActivity : AppCompatActivity() {

    private val activity = this@LoginActivity
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        skipIfLoggedIn()

        binding.btnLogin.alpha = 0f
        binding.btnLogin.animate().alpha(1f).duration = 1500

        binding.btnLogin.setOnClickListener {
            binding.btnLogin.alpha = 0f
            binding.btnLogin.animate().alpha(1f).duration = 1500
            login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }

        binding.btnRegister.setOnClickListener {
            binding.btnLogin.alpha = 0f
            binding.btnLogin.animate().alpha(1f).duration = 1500
            register()
        }
    }

    private fun login(username: String, password: String) {
        val loggedInUser = LoginManager.login(this, username, password)

        if (loggedInUser != null){
            loginSuccessful(loggedInUser)
        }
        else {
            loginFailed()
        }
    }

    private fun register() {
        val accountsIntent = Intent(activity, RegisterActivity::class.java)
        emptyInputEditText()
        startActivity(accountsIntent)
    }

    private fun loginSuccessful(user: User) {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        sharedPreferencesManager.putObject(user.id, PREF_USER_ID)
        sharedPreferencesManager.putObject(user.name, PREF_USERNAME)

        val accountsIntent = Intent(activity, MainMenuActivity::class.java)
        emptyInputEditText()
        startActivity(accountsIntent)
        Toast.makeText(this, "Zalogowano poprawnie.", Toast.LENGTH_LONG).show()
    }

    private fun loginFailed() {
        Toast.makeText(this, "Nieprawidłowy login lub hasło.", Toast.LENGTH_LONG).show()
    }

    private fun emptyInputEditText() {
        binding.etUsername.text = null
        binding.etPassword.text = null
    }

    private fun skipIfLoggedIn() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val logedInUsername = sharedPreferencesManager.getObject<String>(PREF_USER_ID)

        if (logedInUsername != null) {
            val accountsIntent = Intent(activity, MainMenuActivity::class.java)
            startActivity(accountsIntent)
            finish()
        }
    }
}
