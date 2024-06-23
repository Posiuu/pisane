package com.example.pisane.controler.background_worker

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import com.example.pisane.controler.background_worker.common.RequestMethods
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

abstract class AbstractBackgroundWorker(ctx: Context, urlString: String, requestMethod: RequestMethods) : AsyncTask<String?, Void?, String?>() {
    @SuppressLint("StaticFieldLeak")
    val context: Context = ctx
    private val url = URL(urlString)
    private val requestMethodString = requestMethod.name

    override fun doInBackground(vararg params: String?): String? {
        try {
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = requestMethodString
            httpURLConnection.doOutput = true
            httpURLConnection.doInput = true
            val outputStream = httpURLConnection.outputStream
            val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
            val postData = getPostDataString(params)
            if (!postData.isNullOrBlank()){
                bufferedWriter.write(postData)
            }
            bufferedWriter.flush()
            bufferedWriter.close()
            outputStream.close()
            val inputStream = httpURLConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1"))
            var result: String? = ""
            var line: String?
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

        return null
    }

    abstract fun getPostDataString(params: Array<out String?>): String?
}