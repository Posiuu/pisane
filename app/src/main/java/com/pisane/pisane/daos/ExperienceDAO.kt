package com.pisane.pisane.daos

import com.pisane.pisane.consts.get_user_experience_url
import com.pisane.pisane.consts.update_user_experience_url
import com.pisane.pisane.enums.ResultStatus
import com.vishnusivadas.advanced_httpurlconnection.PutData

class ExperienceDAO {
    companion object {
        fun updateUserExperience(userId: Int, experience: Int): Boolean {
            val putData = PutData(
                update_user_experience_url,
                "POST",
                arrayOf("userId", "experience"),
                arrayOf(userId.toString(), experience.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                if (putData.result == ResultStatus.SUCCESS.name){
                    return true
                }
            }

            return false
        }

        fun getUserExperience(userId: Int): Int {
            val putData = PutData(
                get_user_experience_url,
                "POST",
                arrayOf("userId"),
                arrayOf(userId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                val result = putData.result
                return if(!result.isNullOrEmpty()) result.toInt() else 0
            }

            return 0
        }
    }
}