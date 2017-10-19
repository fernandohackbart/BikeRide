package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class GeneratePINRequest(val client: BikerClient = BikerClient("INVALID"),
                              val email: String = "",
                              val mobile: String = "") {

    class Deserializer : ResponseDeserializable<GeneratePINRequest> {
        override fun deserialize(content: String) = Gson().fromJson(content, GeneratePINRequest::class.java)
    }

}
