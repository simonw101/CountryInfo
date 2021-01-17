package com.example.countryinfo

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import java.text.DecimalFormat
import kotlin.reflect.typeOf

private const val TAG = "ParseJsonData"

class ParseJsonData {

    var countryArea :Int? = null

    fun parse(data: String) : CountryModel {

        val countryModel = CountryModel()

        try {

            val jsonArray = JSONArray(data)

            for (i in 0 until jsonArray.length()) {

                val countryName = jsonArray.getJSONObject(i).getString("name")

                val countryDomain = jsonArray.getJSONObject(i).getJSONArray("topLevelDomain")[0]

                val countryCallingCode = jsonArray.getJSONObject(i).getJSONArray("callingCodes")[0]

                val capitalCity = jsonArray.getJSONObject(i).getString("capital")

                val population = jsonArray.getJSONObject(i).getInt("population")

                countryArea = jsonArray.getJSONObject(i).optInt("area", 0)


                countryModel.countryName = countryName
                countryModel.countryDomain = countryDomain.toString()
                countryModel.countryCallingCode = countryCallingCode.toString()
                countryModel.countryCapital = capitalCity
                countryModel.countryPopulation = formattedNumber(population)
                countryModel.countryArea = formattedNumber(countryArea!!)



                Log.i(TAG, countryModel.countryName)
                Log.i(TAG, countryModel.countryDomain)
                Log.i(TAG, countryModel.countryCallingCode)
                Log.i(TAG, countryModel.countryCapital)
                Log.i(TAG, countryModel.countryPopulation)
                Log.i(TAG, countryModel.countryArea)

            }

        } catch (e: JSONException) {

            e.printStackTrace()

        }

        return countryModel

    }

    private fun formattedNumber(population: Int) : String {

        val formatter = DecimalFormat("#,###,###")

        return formatter.format(population)

    }

}