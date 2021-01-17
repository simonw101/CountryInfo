package com.example.countryinfo

import android.util.Log
import org.json.JSONArray
import org.json.JSONException

private const val TAG = "ParseJsonData"

class ParseJsonData {

    fun parse(data: String) : CountryModel {

        val countryModel = CountryModel()

        try {

            val jsonArray = JSONArray(data)

            for (i in 0 until jsonArray.length()) {

                val countryName = jsonArray.getJSONObject(i).getString("name")

                countryModel.countryName = countryName

                Log.i(TAG, countryModel.countryName)

            }

        } catch (e: JSONException) {

            e.printStackTrace()

        }

        return countryModel

    }

}