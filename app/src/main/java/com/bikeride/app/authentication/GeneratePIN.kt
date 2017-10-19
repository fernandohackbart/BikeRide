package com.bikeride.app.authentication

import android.app.Activity
import android.os.Bundle
import com.bikeride.app.api.Authentication
import com.bikeride.app.api.data.BikerClient
import com.bikeride.app.api.data.GeneratePINRequest
import com.bikeride.app.utils.Preferences
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class GeneratePIN : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            val generateContext = this.context
            val preferences: Preferences? = Preferences(context)
            val clientID = preferences!!.clientID
            textView("Generate PIN")
            val email = editText{
                hint = "enter your email"
            }
            val mobile = editText{
                hint = "enter your mobile"
            }
            button("Generate PIN") {
                onClick {
                    Authentication.generatePIN(generateContext,GeneratePINRequest(BikerClient(clientID),email.text.toString(),mobile.text.toString()))
                    alert(title = "Generate PIN",
                            message = "PIN generated should arrive in the email ou SMS") {
                        positiveButton("Close") { //this@alert.dismiss()
                        }
                    }.show()
                }
            }
        }
    }
}