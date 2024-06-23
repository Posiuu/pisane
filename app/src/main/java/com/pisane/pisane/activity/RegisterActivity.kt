package com.pisane.pisane.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.consts.signup_url
import com.pisane.pisane.databinding.ActivityRegisterBinding
import com.pisane.pisane.enums.ResultStatus
import com.vishnusivadas.advanced_httpurlconnection.PutData


class RegisterActivity : AppCompatActivity() {

    private val activity = this@RegisterActivity
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.alpha = 0f
        binding.btnRegister.animate().alpha(1f).duration = 1500

        binding.btnRegister.setOnClickListener {
            binding.btnRegister.alpha = 0f
            binding.btnRegister.animate().alpha(1f).duration = 1500
            register(binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }

        binding.btnBackToLogin.setOnClickListener {
            binding.btnBackToLogin.alpha = 0f
            binding.btnBackToLogin.animate().alpha(1f).duration = 1500
            backToLogin()
        }
    }

    private fun register(username: String?, password: String?) {
        Handler(Looper.getMainLooper()).post {
            if (username.isNullOrEmpty() || password.isNullOrEmpty()){
                Toast.makeText(this, "Login i hasło wymagane", Toast.LENGTH_LONG).show()
                return@post
            }
            val putData = PutData(
                signup_url,
                "POST",
                arrayOf("username", "password"),
                arrayOf(username, password)
            )
            if (putData.startPut() && putData.onComplete()) {
                when (putData.result){
                    ResultStatus.SUCCESS.name -> registerSuccessful()
                    ResultStatus.FAIL_MISSING_VALUES.name -> Toast.makeText(this, "Login i hasło wymagane", Toast.LENGTH_LONG).show()
                    ResultStatus.FAIL_OTHER.name -> Toast.makeText(this, "Nazwa zajęta przez innego gracza", Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(this, "Nie udało się połączyć z serwerem. Włącz wifi lub transfer danych i spróbuj ponownie", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun registerSuccessful() {
        emptyInputEditText()
        Toast.makeText(this, "Nowe konto utworzone.", Toast.LENGTH_LONG).show()
    }

    private fun backToLogin() {
        emptyInputEditText()
        val accountsIntent = Intent(activity, LoginActivity::class.java)
        startActivity(accountsIntent)
    }

    private fun emptyInputEditText() {
        binding.etUsername.text = null
        binding.etPassword.text = null
    }
}
