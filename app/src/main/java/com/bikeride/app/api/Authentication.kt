package com.bikeride.app.api

import android.content.Context
import com.bikeride.app.api.data.BikerCreateRequest
import com.bikeride.app.api.data.BikerToken
import com.bikeride.app.api.data.GeneratePINRequest
import com.bikeride.app.api.data.GeneratePINResponse
import com.bikeride.app.utils.Preferences
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.google.gson.GsonBuilder


object Authentication {

    fun defaults(context: Context) {
        val preferences: Preferences? = Preferences(context)
        preferences!!.clientToken
        FuelManager.instance.basePath = preferences!!.APIURL
    }

    fun createBiker(context: Context, createBikerrequest: BikerCreateRequest) {
        defaults(context)
        val preferences: Preferences? = Preferences(context)
        val gson = GsonBuilder().setPrettyPrinting().create()
        Fuel.post("/api/authn/biker").body(gson.toJson(createBikerrequest)).responseObject(BikerToken.Deserializer()){ req, res, result ->
            val (bikerTokenRsp, err) = result
             preferences?.clientToken=bikerTokenRsp?.token!!.authToken
            preferences?.bikerID=bikerTokenRsp?.bikerID!!.bikerID
        }
    }

    fun generatePIN(context: Context,pinRequest: GeneratePINRequest){
        defaults(context)
        val gson = GsonBuilder().setPrettyPrinting().create()
        Fuel.post("/api/authn/generatepin").body(gson.toJson(pinRequest)).responseObject(GeneratePINResponse.Deserializer())
    }
}
