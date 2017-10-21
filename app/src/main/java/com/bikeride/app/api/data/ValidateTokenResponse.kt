package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class ValidateTokenResponse(val valid: Boolean = false) {

    class Deserializer : ResponseDeserializable<ValidateTokenResponse> {
        override fun deserialize(content: String) = Gson().fromJson(content, ValidateTokenResponse::class.java)
    }

}

