package com.bikeride.app.api

import android.content.Context
import com.bikeride.app.api.data.Biker
import com.bikeride.app.utils.Preferences
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager


object Biker {

    fun defaults(context: Context) {
        val preferences: Preferences? = Preferences(context)
        preferences!!.clientToken
        FuelManager.instance.basePath = preferences!!.APIURL
        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json",
                "bikerideClientID" to preferences!!.clientID,
                "bikerideClientToken" to preferences!!.clientToken)
    }


    fun getBiker(context: Context): Biker? {
        defaults(context)
        val preferences: Preferences? = Preferences(context)
        var bikerReturn: Biker? = null
        Fuel.get("/api/biker/" + preferences!!.bikerID).responseObject(Biker.Deserializer()) { req, res, result ->
            val (bikerRsp, err) = result
            bikerReturn = bikerRsp
        }
        return bikerReturn
    }
}


