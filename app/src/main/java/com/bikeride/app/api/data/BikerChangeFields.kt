package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class BikerChangeFields(
        val name: String = "",
        val avatarb64: String? = null,
        val bloodType: String? = null,
        val mobile: String? = null,
        val email: String? = null){

    class Deserializer : ResponseDeserializable<BikerChangeFields> {
        override fun deserialize(content: String) = Gson().fromJson(content, BikerChangeFields::class.java)
    }

}