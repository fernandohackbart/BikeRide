package com.bikeride.app.api.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson



data class Token(val authToken: String = "",
                  val refreshToken: String = "") {

    class Deserializer : ResponseDeserializable<Token> {
        override fun deserialize(content: String) = Gson().fromJson(content, Token::class.java)
    }

}



//case class BikerToken(bikerID: BikerID,token: Token)

