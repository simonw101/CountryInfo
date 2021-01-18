package com.example.countryinfo

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import java.text.DecimalFormat

private const val TAG = "ParseJsonData"

class ParseJsonData {

    fun parse(data: String): CountryModel {

        val countryModel = CountryModel()

        try {

            val jsonArray = JSONArray(data)

            for (i in 0 until jsonArray.length()) {

                val countryName = jsonArray.getJSONObject(i).getString("name")

                val countryDomain = jsonArray.getJSONObject(i).getJSONArray("topLevelDomain")[0]

                val countryCallingCode = jsonArray.getJSONObject(i).getJSONArray("callingCodes")[0]

                val capitalCity = jsonArray.getJSONObject(i).getString("capital")

                val population = jsonArray.getJSONObject(i).getInt("population")

                val countryArea = jsonArray.getJSONObject(i).optInt("area", 0)

                val currencies = jsonArray.getJSONObject(i).getJSONArray("currencies")

                val flagUrl = jsonArray.getJSONObject(i).getString("flag")

                for (i in 0 until currencies.length()) {

                    val item = currencies.getJSONObject(i)

                    Log.i(TAG, item.getString("name"))

                    countryModel.countryCurrencies.add(item.getString("name"))

                }

                val languages = jsonArray.getJSONObject(i).getJSONArray("languages")

                for (i in 0 until languages.length()) {

                    val item = languages.getJSONObject(i)

                    Log.i(TAG, item.getString("name"))

                    countryModel.countryMainLanguages.add(item.getString("name"))

                }

                val timeZones = jsonArray.getJSONObject(i).getJSONArray("timezones")

                for (i in 0 until timeZones.length()) {

                    val item = timeZones[i] as? String

                    Log.i(TAG, item)

                }


                countryModel.countryName = countryName
                countryModel.countryDomain = countryDomain.toString()
                countryModel.countryCallingCode = countryCallingCode.toString()
                countryModel.countryCapital = capitalCity
                countryModel.countryPopulation = formattedNumber(population)
                countryModel.countryArea = formattedNumber(countryArea)
                countryModel.countryFlagUrl = flagUrl


                Log.i(TAG, countryModel.countryName)
                Log.i(TAG, countryModel.countryDomain)
                Log.i(TAG, countryModel.countryCallingCode)
                Log.i(TAG, countryModel.countryCapital)
                Log.i(TAG, countryModel.countryPopulation)
                Log.i(TAG, countryModel.countryArea)
                Log.i(TAG, countryModel.countryFlagUrl)


            }

        } catch (e: JSONException) {

            e.printStackTrace()

        }

        return countryModel

    }

    private fun formattedNumber(population: Int): String {

        val formatter = DecimalFormat("#,###,###")

        return formatter.format(population)

    }

}