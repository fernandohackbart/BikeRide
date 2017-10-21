package com.bikeride.app.authentication

import android.app.Activity
import android.os.Bundle
import com.bikeride.app.api.Defaults
import com.bikeride.app.api.data.*
import com.bikeride.app.utils.Preferences
import com.github.kittinunf.fuel.Fuel
import com.google.gson.GsonBuilder
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class ValidatePIN : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            val preferences: Preferences? = Preferences(context)
            val clientID = preferences!!.clientID
            val gson = GsonBuilder().setPrettyPrinting().create()
            Defaults.authenticationDefaults(context)

            textView("Validate PIN")
            val pin = editText{
                hint = "enter your PIN"
            }

            button("Validate PIN") {
                onClick {
                    Fuel.post("/api/authn/validatepin").body(gson.toJson(ValidatePINRequest(BikerClient(clientID),pin.text.toString()))).responseObject(BikerToken.Deserializer()){ req, res, result ->
                        val (tokenRsp, err) = result
                        if (err != null) {
                            alert(title = "Error",
                                    message = err.message.orEmpty()) {
                                positiveButton("Close") {
                                }
                            }.show()
                        } else {
                            preferences?.clientToken=tokenRsp?.token!!.authToken
                            preferences?.bikerID=tokenRsp?.bikerID!!.bikerID
                            toast("PIN validated")
                        }
                    }
                }
            }
        }
    }
}