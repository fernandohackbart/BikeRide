package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class BikerToken(val bikerID: BikerID = BikerID("INVALID"),
                 val token: Token = Token("INVALID","INVALID")) {

    class Deserializer : ResponseDeserializable<BikerToken> {
        override fun deserialize(content: String) = Gson().fromJson(content, BikerToken::class.java)
    }

}