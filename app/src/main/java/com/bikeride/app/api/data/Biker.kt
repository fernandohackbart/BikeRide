package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Biker(val bikerID: BikerID,
                 val bikerFields: BikerFields) {

    class Deserializer : ResponseDeserializable<Biker> {
        override fun deserialize(content: String) = Gson().fromJson(content, Biker::class.java)
    }

}
