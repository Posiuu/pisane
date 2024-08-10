package com.pisane.pisane.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pisane.pisane.R
import com.pisane.pisane.consts.login_url
import com.pisane.pisane.consts.signup_url
import com.pisane.pisane.databinding.ActivityRegisterBinding
import com.pisane.pisane.enums.ResultStatus
import com.pisane.pisane.model.User
import com.pisane.pisane.services.MusicService
import com.pisane.pisane.shared_preferences.PREF_USERNAME
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesManager
import com.vishnusivadas.advanced_httpurlconnection.PutData


class RegisterActivity : AppCompatActivity() {

    private val activity = this@RegisterActivity
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startService(Intent(this, MusicService::class.java))
        skipIfLoggedIn()

        binding.rRegisterButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_button_click).start()
            register(binding.rUsernameEditText.text.toString(), binding.rPasswordEditText.text.toString())
        }

        binding.rToLoginButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_go_back).start()
            backToLogin()
        }
    }

    private fun register(username: String?, password: String?) {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()){
            Toast.makeText(this, "Login i hasło wymagane", Toast.LENGTH_LONG).show()
            return
        }
        val putData = PutData(
            signup_url,
            "POST",
            arrayOf("username", "password"),
            arrayOf(username, password)
        )
        if (putData.startPut() && putData.onComplete()) {
            when (putData.result){
                ResultStatus.SUCCESS.name -> login(username, password)
                ResultStatus.FAIL_MISSING_VALUES.name -> Toast.makeText(this, "Login i hasło wymagane", Toast.LENGTH_LONG).show()
                ResultStatus.FAIL_OTHER.name -> Toast.makeText(this, "Nazwa zajęta przez innego gracza", Toast.LENGTH_LONG).show()
                else -> Toast.makeText(this, "Nie udało się połączyć z serwerem. Włącz wifi lub transfer danych i spróbuj ponownie", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun login(username: String, password: String) {
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
                val sharedPreferencesManager = SharedPreferencesManager(this)
                sharedPreferencesManager.putObject(user.id, PREF_USER_ID)
                sharedPreferencesManager.putObject(user.username, PREF_USERNAME)

                val accountsIntent = Intent(activity, MainMenuActivity::class.java)
                startActivity(accountsIntent)
                finish()
                Toast.makeText(this, "Nowe konto utworzone.", Toast.LENGTH_LONG).show()
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

    private fun backToLogin() {
        val accountsIntent = Intent(activity, LoginActivity::class.java)
        startActivity(accountsIntent)
        finish()
    }

    private fun skipIfLoggedIn() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val loggedInUsername = sharedPreferencesManager.getObject<String>(PREF_USER_ID)

        if (loggedInUsername != null) {
            val accountsIntent = Intent(activity, MainMenuActivity::class.java)
            startActivity(accountsIntent)
            finish()
        }
    }
}
