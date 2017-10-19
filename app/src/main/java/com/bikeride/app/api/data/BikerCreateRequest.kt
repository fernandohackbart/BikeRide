package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class BikerCreateRequest(val client: BikerClient,
                              val bikerFields: BikerFields) {

    class Deserializer : ResponseDeserializable<BikerCreateRequest> {
        override fun deserialize(content: String) = Gson().fromJson(content, BikerCreateRequest::class.java)
    }

}



