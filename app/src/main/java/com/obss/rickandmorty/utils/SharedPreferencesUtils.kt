package com.obss.rickandmorty.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

object SharedPreferencesUtils {

    fun addFavoriteCharacter(context: Context, favoriteCharacterIdList: ArrayList<Int>) {
        val gson = Gson()
        val json = gson.toJson(favoriteCharacterIdList)
        putString(context, "CHARACTER_ID", json)
    }


    fun getFavoriteCharacterIdList(context: Context): ArrayList<Int> {
        val characterIdList: ArrayList<Int>
        val gson = Gson()
        val json = getString(context, "CHARACTER_ID", "")
        characterIdList = try {
            if (json?.isEmpty() == true || json.equals("[]")) {
                ArrayList()
            } else {
                val type = object : TypeToken<ArrayList<Int>>() {

                }.type
                gson.fromJson(json, type)
            }
        } catch (e: JsonSyntaxException) {
            return ArrayList()
        }

        return characterIdList
    }

    fun putString(context: Context, key: String, value: String?) {
        val pref = context.getSharedPreferences("FAVORITE_IDS", Context.MODE_PRIVATE)
        pref.edit().putString(key, value).apply()
    }

    private fun getString(context: Context, key: String, defValue: String): String? {
        val pref = context.getSharedPreferences("FAVORITE_IDS", Context.MODE_PRIVATE)
        return pref.getString(key, defValue)
    }
}