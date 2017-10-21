package com.bikeride.app.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.bikeride.app.api.APIDefaults
import com.bikeride.app.api.data.*
import com.bikeride.app.utils.Preferences
import com.github.kittinunf.fuel.Fuel
import com.google.gson.GsonBuilder
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class Authentication : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val validatePINIntent = Intent(this, ValidatePIN::class.java)
        val generatePINIntent = Intent(this, GeneratePIN::class.java)
        val createBikerIntent = Intent(this, CreateBiker::class.java)

        verticalLayout {

            val gson = GsonBuilder().setPrettyPrinting().create()
            val preferences: Preferences? = Preferences(context)
            APIDefaults.authenticationDefaults(context)
            val clientID = preferences!!.clientID

            textView("Authentication") {
                textSize = 19f
            }


                button("Generate PIN") {
                onClick { startActivity(generatePINIntent) }
            }

            button("Validate PIN") {
                onClick { startActivity(validatePINIntent) }
            }

            button("Create biker") {
                onClick { startActivity(createBikerIntent) }
            }

            button("Show biker ID") {
                onClick {
                    alert(title = "Biker ID ",
                            message = "${preferences!!.bikerID}") {
                        positiveButton("Close") {}
                    }.show()
                }
            }
            button("Show client ID") {
                onClick {
                    alert(title = "Client ID",
                            message = "${preferences!!.clientID}") {
                        positiveButton("Close") {}
                    }.show()
                }
            }
            button("Show token") {
                onClick {
                    alert(title = "Token",
                            message = "${preferences!!.clientToken}") {
                        positiveButton("Close") {}
                    }.show()
                }
            }

            button("Validate token") {
                onClick {
                    Fuel.post("/api/authn/validatetoken").body(gson.toJson(BikerToken(BikerID(preferences!!.bikerID),
                            Token(preferences!!.clientToken,"")))).responseObject(ValidateTokenResponse.Deserializer()){ req, res, result ->
                        val (tokenRsp, err) = result
                        if (err != null) {
                            alert(title = "Error",
                                    message = err.message.orEmpty()) {
                                positiveButton("Close") {}
                            }.show()
                        } else {
                            if(tokenRsp!!.valid){
                                toast("Token is valid")
                            }else toast("Token is NOT valid")
                        }
                    }
                }
            }

            button("Refresh token") {
                onClick {
                    Fuel.post("/api/authn/refreshtoken").body(gson.toJson(RefreshTokenRequest(
                            BikerClient(clientID),BikerToken(BikerID(preferences!!.bikerID),
                            Token(preferences!!.clientToken,""))))).responseObject(BikerToken.Deserializer()){ req, res, result ->
                        val (tokenRsp, err) = result
                        if (err != null) {
                            alert(title = "Error",
                                    message = err.message.orEmpty()) {
                                positiveButton("Close") {}
                            }.show()
                        } else {
                            preferences?.clientToken=tokenRsp?.token!!.authToken
                            longToast("Token refreshed, new toke: ${tokenRsp?.token!!.authToken}")
                        }
                    }
                }
            }
        }
    }
}
