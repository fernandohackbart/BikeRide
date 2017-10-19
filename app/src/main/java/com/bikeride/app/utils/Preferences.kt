package com.bikeride.app.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class Preferences(context: Context) {
    val PREFS_FILENAME = "com.bikeride.app.utils.preferences"
    val CLIENT_ID = "client_id"
    val CLIENT_TOKEN = "client_token"
    val BIKER_ID = "biker_id"
    val API_URL = "api_url"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME,Context.MODE_PRIVATE);

    var APIURL: String
        get () = prefs.getString(API_URL, "http://192.168.1.200:8000")
        set (value) = prefs.edit().putString(API_URL, value).apply()

    var bikerID: String
        get () = prefs.getString(BIKER_ID, "INVALID")
        set (value) = prefs.edit().putString(BIKER_ID, value).apply()

    var clientToken: String
        get () = prefs.getString(CLIENT_TOKEN, "INVALID")
        set (value) = prefs.edit().putString(CLIENT_TOKEN, value).apply()

    var clientID: String = "INVALID"
      get() {
          if (!prefs.contains(CLIENT_ID)){
              prefs.edit().putString(CLIENT_ID, UUID.randomUUID().toString()).apply()
          }
          return prefs.getString(CLIENT_ID,"INVALID")
      }
}