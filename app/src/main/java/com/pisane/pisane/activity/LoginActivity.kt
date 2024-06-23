package com.pisane.pisane.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pisane.pisane.consts.login_url
import com.pisane.pisane.controler.shared_preferences.PREF_USERNAME
import com.pisane.pisane.controler.shared_preferences.SharedPreferencesManager
import com.pisane.pisane.databinding.ActivityLoginBinding
import com.pisane.pisane.controler.shared_preferences.PREF_USER_ID
import com.pisane.pisane.enums.ResultStatus
import com.pisane.pisane.model.User
import com.vishnusivadas.advanced_httpurlconnection.PutData

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

    private fun login(username: String?, password: String?) {
        Handler(Looper.getMainLooper()).post {
            if (username.isNullOrEmpty() || password.isNullOrEmpty()){
                Toast.makeText(this, "Login i hasło wymagane", Toast.LENGTH_LONG).show()
                return@post
            }
            val putData = PutData(
                login_url,
                "POST",
                arrayOf("username", "password"),
                arrayOf(username, password)
            )
            if (putData.startPut() && putData.onComplete()) {
                val result = putData.result
                val user: User? = Gson().fromJson(result, object : TypeToken<User>() {}.type)

                if (user != null){
                    loginSuccessful(user)
                }
                else {
                    when (result){
                        ResultStatus.FAIL_MISSING_VALUES.name -> Toast.makeText(this, "Login i hasło wymagane", Toast.LENGTH_LONG).show()
                        ResultStatus.FAIL_DATABASE_CONNECTION.name -> Toast.makeText(this, "Nie udało się połączyć z serwerem. Włącz wifi lub transfer danych i spróbuj ponownie", Toast.LENGTH_LONG).show()
                        ResultStatus.FAIL_OTHER.name -> Toast.makeText(this, "Niepoprawne dane logowania", Toast.LENGTH_LONG).show()
                        else -> Toast.makeText(this, "Błąd podczas logowania, spróbuj ponownie", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun loginSuccessful(user: User) {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        sharedPreferencesManager.putObject(user.id, PREF_USER_ID)
        sharedPreferencesManager.putObject(user.username, PREF_USERNAME)

        emptyInputEditText()
        val accountsIntent = Intent(activity, MainMenuActivity::class.java)
        startActivity(accountsIntent)
        Toast.makeText(this, "Zalogowano poprawnie.", Toast.LENGTH_LONG).show()
    }

    private fun register() {
        val accountsIntent = Intent(activity, RegisterActivity::class.java)
        emptyInputEditText()
        startActivity(accountsIntent)
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
