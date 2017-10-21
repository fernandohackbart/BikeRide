package com.bikeride.app.authentication

import android.app.Activity
import android.os.Bundle
import com.bikeride.app.api.APIDefaults
import com.bikeride.app.api.data.*
import com.bikeride.app.utils.Preferences
import com.github.kittinunf.fuel.Fuel
import com.google.gson.GsonBuilder
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class CreateBiker : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val preferences: Preferences? = Preferences(context)
            APIDefaults.authenticationDefaults(context)

            val clientID = preferences!!.clientID
            textView("Create biker") {
                textSize = 19f
            }
            val name = editText{
                hint = "enter your name"
            }
            val email = editText{
                hint = "enter your email"
            }
            val mobile = editText{
                hint = "enter your mobile"
            }
            button("Become biker") {
                onClick {
                    Fuel.post("/api/authn/biker").body(gson.toJson(BikerCreateRequest(BikerClient(clientID),
                            BikerFields(name =  name.text.toString(),
                                    email = email.text.toString(),
                                    mobile = mobile.text.toString())))).responseObject(BikerToken.Deserializer()){ req, res, result ->
                        val (bikerTokenRsp, err) = result
                        if (err != null) {
                            alert(title = "Error",
                                    message = err.message.orEmpty()) {
                                positiveButton("Close") {
                                }
                            }.show()
                        } else {
                            preferences?.clientToken=bikerTokenRsp?.token!!.authToken
                            preferences?.bikerID=bikerTokenRsp?.bikerID!!.bikerID
                            toast("Biker created")
                        }
                    }
                }
            }
        }
    }
}
