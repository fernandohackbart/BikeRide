package com.bikeride.app.authentication

import android.app.Activity
import android.os.Bundle
import com.bikeride.app.api.Defaults
import com.bikeride.app.api.data.*
import com.bikeride.app.utils.Preferences
import com.github.kittinunf.fuel.Fuel
import com.google.gson.GsonBuilder
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class GeneratePIN : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val preferences: Preferences? = Preferences(context)
            Defaults.authenticationDefaults(context)
            val clientID = preferences!!.clientID

            textView("Generate PIN")

            val email = editText{
                hint = "enter your email"
            }
            button("Request PIN by email") {
                onClick {
                    Fuel.post("/api/authn/generatepin").body(gson.toJson(GeneratePINRequest(BikerClient(clientID),email.text.toString(),""))).responseObject(GeneratePINResponse.Deserializer()){ req, res, result ->
                        val (tokenRsp, err) = result
                        if (err != null) {
                            alert(title = "Error",
                                    message = err.message.orEmpty()) {
                                positiveButton("Close") {
                                }
                            }.show()
                        } else {
                            println("##### PIN is: ${tokenRsp.toString()} result: ${err}")
                            toast("PIN requested to be received by email, but we are lazy and added to the response: ${tokenRsp.toString()}")
                        }
                    }
                }
            }

            val mobile = editText{
                hint = "enter your mobile"
            }
            button("Request PIN by SMS") {
                onClick {
                    Fuel.post("/api/authn/generatepin").body(gson.toJson(GeneratePINRequest(BikerClient(clientID),"",mobile.text.toString()))).responseObject(GeneratePINResponse.Deserializer()){ req, res, result ->
                        val (tokenRsp, err) = result
                        if (err != null) {
                            alert(title = "Error",
                                    message = err.message.orEmpty()) {
                                positiveButton("Close") {
                                }
                            }.show()
                        } else {
                            println("##### PIN is: ${tokenRsp.toString()} result: ${err}")
                            toast("PIN requested to be received by SMS, but we are lazy and added to the response: ${tokenRsp.toString()}")
                        }
                    }
                }
            }
        }
    }
}