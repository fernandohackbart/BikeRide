package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class BikerID(val bikerID: String = "") {

    class Deserializer : ResponseDeserializable<BikerID> {
        override fun deserialize(content: String) = Gson().fromJson(content, BikerID::class.java)
    }
}
