package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class BikerFields(
        val name: String = "",
        val avatarb64: String? = null,
        val bloodType: String? = null,
        val mobile: String? = null,
        val email: String? = null,
        val active: Boolean = true){

    class Deserializer : ResponseDeserializable<BikerFields> {
        override fun deserialize(content: String) = Gson().fromJson(content, BikerFields::class.java)
    }

}