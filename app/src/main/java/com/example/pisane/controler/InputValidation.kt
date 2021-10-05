package com.example.pisane.controler

import android.content.Context
import android.widget.EditText

class InputValidation
/**
 * constructor
 *
 * @param context
 */
(private val context: Context) {
    /**
     * method to check InputEditText filled .
     *
     * @param textInputEditText
     * @return
     */
    fun isInputEditTextFilled(textInputEditText: EditText): Boolean {
        val value = textInputEditText.text.toString().trim()
        return value.isNotEmpty()
    }
    /**
     * method to check InputEditText has valid email .
     *
     * @param textInputEditText
     * @return
     */
    fun isInputEditTextEmail(textInputEditText: EditText): Boolean {
        val value = textInputEditText.text.toString().trim()
        return value.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
    /**
     * method to check both InputEditText value matches.
     *
     * @param textInputEditText1
     * @param textInputEditText2
     * @return
     */
    fun isInputEditTextMatches(textInputEditText1: EditText, textInputEditText2: EditText): Boolean {
        val value1 = textInputEditText1.text.toString().trim()
        val value2 = textInputEditText2.text.toString().trim()

        return value1.contentEquals(value2)
    }
}