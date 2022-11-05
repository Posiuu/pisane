package com.example.pisane.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.controler.background_worker.RegisterBackgroundWorker
import com.example.pisane.controler.background_worker.common.RequestMethods
import com.example.pisane.controler.background_worker.common.ResultStatus
import com.example.pisane.controler.background_worker.common.register_url
import com.example.pisane.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val activity = this@RegisterActivity
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.alpha = 0f;
        binding.btnRegister.animate().alpha(1f).duration = 1500;

        binding.btnRegister.setOnClickListener {
            binding.btnRegister.alpha = 0f;
            binding.btnRegister.animate().alpha(1f).duration = 1500;
            register(binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }

        binding.btnBackToLogin.setOnClickListener {
            binding.btnBackToLogin.alpha = 0f;
            binding.btnBackToLogin.animate().alpha(1f).duration = 1500;
            backToLogin()
        }
    }

    private fun register(username: String, password: String) {
        val backgroundWorker = RegisterBackgroundWorker(this, register_url, RequestMethods.POST)
        backgroundWorker.execute(username, password)

        val result = backgroundWorker.get().toString()
        when (result){
            ResultStatus.SUCCESS.name -> registerSuccessful()
            ResultStatus.FAIL.name -> registerFailed()
        }
    }

    private fun registerSuccessful() {
        emptyInputEditText()
        Toast.makeText(this, "Nowe konto utworzone.", Toast.LENGTH_LONG).show()
    }

    private fun registerFailed() {
        Toast.makeText(this, "Nie udało się zarejestrować. Prawdopodobnie wybrana nazwa użytkownika jest już zajęta.", Toast.LENGTH_LONG).show()
    }

    private fun backToLogin() {
        val accountsIntent = Intent(activity, LoginActivity::class.java)
        emptyInputEditText()
        startActivity(accountsIntent)
    }

    private fun emptyInputEditText() {
        binding.etUsername.text = null
        binding.etPassword.text = null
    }
}
