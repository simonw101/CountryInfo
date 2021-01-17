package com.example.countryinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import java.io.IOException

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://restcountries.eu/rest/v2/all"

        try {
            run(url)
        } catch (e: Exception) {

            e.printStackTrace()

        }

    }

    private fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (response.isSuccessful) {

                        val result = response.body?.string()

                        Log.i(TAG, result)


                    }

                }
            }
        })
    }
}