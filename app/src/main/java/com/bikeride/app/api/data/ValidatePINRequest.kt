package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class ValidatePINRequest(val client: BikerClient = BikerClient("INVALID"),
                      val pin: String = "INVALID") {

    class Deserializer : ResponseDeserializable<ValidatePINRequest> {
        override fun deserialize(content: String) = Gson().fromJson(content, ValidatePINRequest::class.java)
    }

}