package com.pisane.pisane.controler.login

import android.content.Context
import at.favre.lib.crypto.bcrypt.BCrypt
import com.pisane.pisane.controler.daos.LoginDAO
import com.pisane.pisane.model.User

class LoginManager {
    companion object {
        fun login(context: Context, username: String, password: String): User? {
            var result: User? = null

            val userIdAndPass = LoginDAO.getUserIdAndPassByName(context, username)
            val userId = userIdAndPass.id.toInt()
            val bCryptResult = BCrypt.verifyer().verify(password.toCharArray(), userIdAndPass.user_password)
            if (bCryptResult.verified) {
                result = User(userId, username, "")
            }

            return result
        }
    }
}