package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class BikerClient(val clientID: String = "") {

    class Deserializer : ResponseDeserializable<BikerClient> {
        override fun deserialize(content: String) = Gson().fromJson(content, BikerClient::class.java)
    }

}