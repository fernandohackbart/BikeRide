package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class RefreshTokenRequest(val client: BikerClient,
                 val bikerToken: BikerToken) {

    class Deserializer : ResponseDeserializable<RefreshTokenRequest> {
        override fun deserialize(content: String) = Gson().fromJson(content, RefreshTokenRequest::class.java)
    }

}