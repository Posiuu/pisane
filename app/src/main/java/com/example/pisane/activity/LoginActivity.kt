package com.example.pisane.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.controler.shared_preferences_manager.PREF_USERNAME
import com.example.pisane.controler.shared_preferences_manager.SharedPreferencesManager
import com.example.pisane.databinding.ActivityLoginBinding
import com.example.pisane.controler.background_worker.LoginBackgroundWorker
import com.example.pisane.controler.background_worker.common.RequestMethods
import com.example.pisane.controler.background_worker.common.ResultStatus
import com.example.pisane.controler.background_worker.common.login_url

class LoginActivity : AppCompatActivity() {

    private val activity = this@LoginActivity
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.alpha = 0f;
        binding.btnLogin.animate().alpha(1f).duration = 1500;

        binding.btnLogin.setOnClickListener {
            binding.btnLogin.alpha = 0f;
            binding.btnLogin.animate().alpha(1f).duration = 1500;
            login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }

        binding.btnRegister.setOnClickListener {
            binding.btnLogin.alpha = 0f;
            binding.btnLogin.animate().alpha(1f).duration = 1500;
            register()
        }
    }

    private fun login(username: String, password: String) {
        val backgroundWorker = LoginBackgroundWorker(this, login_url, RequestMethods.POST)
        backgroundWorker.execute(username, password)

        val result = backgroundWorker.get().toString()
        when (result){
            ResultStatus.SUCCESS.name -> loginSuccessful(username)
            ResultStatus.FAIL.name -> loginFailed()
        }
    }

    private fun loginSuccessful(username: String) {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        sharedPreferencesManager.putObject(username, PREF_USERNAME)

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

    private fun register() {
        val accountsIntent = Intent(activity, RegisterActivity::class.java)
        emptyInputEditText()
        startActivity(accountsIntent)
    }
}
