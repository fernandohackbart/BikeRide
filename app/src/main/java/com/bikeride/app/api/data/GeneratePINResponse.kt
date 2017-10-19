package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class GeneratePINResponse(val message: String = "") {

    class Deserializer : ResponseDeserializable<GeneratePINResponse> {
        override fun deserialize(content: String) = Gson().fromJson(content, GeneratePINResponse::class.java)
    }
}
