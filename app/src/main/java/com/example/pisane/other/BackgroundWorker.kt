package com.example.pisane.other

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

enum class ResultStatus(s: String) {
    SUCCESS("SUCCESS"),
    FAIL("FAIL")
}

class BackgroundWorker(ctx: Context) : AsyncTask<String?, Void?, String?>() {
    @SuppressLint("StaticFieldLeak")
    val context: Context = ctx

    override fun doInBackground(vararg params: String?): String? {
        val type = params[0]
        val base_url = "https://piotrkroldev-online.preview-domain.com"
        val login_php = "login.php"
        val register_php = "register.php"

        val login_url = "${base_url}/${login_php}"
        val register_url = "${base_url}/${register_php}"


        if (type == "login") {
            try {
                val user_name = params[1]
                val password = params[2]
                val url = URL(login_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doOutput = true
                httpURLConnection.doInput = true
                val outputStream = httpURLConnection.outputStream
                val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
                val post_data = (URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8"))
                bufferedWriter.write(post_data)
                bufferedWriter.flush()
                bufferedWriter.close()
                outputStream.close()
                val inputStream = httpURLConnection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1"))
                var result: String? = ""
                var line: String? = ""
                while (bufferedReader.readLine().also { line = it } != null) {
                    result += line
                }
                bufferedReader.close()
                inputStream.close()
                httpURLConnection.disconnect()
                return result
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        else if (type == "register") {
            try {
                val user_name = params[1]
                val password = params[2]
                val url = URL(register_url)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.doOutput = true
                httpURLConnection.doInput = true
                val outputStream = httpURLConnection.outputStream
                val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
                val post_data = (URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8"))
                bufferedWriter.write(post_data)
                bufferedWriter.flush()
                bufferedWriter.close()
                outputStream.close()
                val inputStream = httpURLConnection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1"))
                var result: String? = ""
                var line: String? = ""
                while (bufferedReader.readLine().also { line = it } != null) {
                    result += line
                }
                bufferedReader.close()
                inputStream.close()
                httpURLConnection.disconnect()
                return result
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }
}