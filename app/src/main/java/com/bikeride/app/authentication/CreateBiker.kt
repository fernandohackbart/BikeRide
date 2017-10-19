package com.bikeride.app.authentication

import android.app.Activity
import android.os.Bundle
import com.bikeride.app.api.Authentication
import com.bikeride.app.api.data.BikerClient
import com.bikeride.app.api.data.BikerCreateRequest
import com.bikeride.app.api.data.BikerFields
import com.bikeride.app.api.data.BikerToken

import com.bikeride.app.utils.Preferences
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class CreateBiker : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            val generateContext = this.context
            val preferences: Preferences? = Preferences(context)
            val clientID = preferences!!.clientID
            textView("Create biker")
            val name = editText{
                hint = "enter your email"
            }
            val email = editText{
                hint = "enter your email"
            }

            val mobile = editText{
                hint = "enter your mobile"
            }
            button("Create biker") {
                onClick {
                    Authentication.createBiker(generateContext,
                            BikerCreateRequest(BikerClient(clientID),
                            BikerFields(name =  name.text.toString(),email = email.text.toString(),mobile = mobile.text.toString())))
                    alert(title = "Create biker ",
                            message = "Biker created!") {
                        positiveButton("Close") { //this@alert.dismiss()
                        }
                    }.show()
                }
            }
        }
    }
}
