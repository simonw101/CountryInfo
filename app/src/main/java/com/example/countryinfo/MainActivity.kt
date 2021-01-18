package com.example.countryinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    private var countryModel = CountryModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = getString(R.string.url)

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

                        if (result != null) {

                            val parseResponse = ParseJsonData().parse(result)

                            countryModel = parseResponse

                        }

                    }

                }
            }
        })
    }
}