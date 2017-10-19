package com.bikeride.app.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.bikeride.app.utils.Preferences
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class Authentication : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var preferences: Preferences? = null
        val generatePINIntent = Intent(this, GeneratePIN::class.java)
        val createBikerIntent = Intent(this, CreateBiker::class.java)

        verticalLayout {
            textView("Authentication")

            button("Generate PIN") {
                onClick { startActivity(generatePINIntent) }
            }

            button("Create biker") {
                onClick { startActivity(createBikerIntent) }
            }

            button("Show biker ID") {
                preferences = Preferences(context)
                onClick {
                    alert(title = "Biker ID ",
                            message = "${preferences!!.bikerID}") {
                        positiveButton("Close") { //this@alert.dismiss()
                        }
                    }.show()
                }
            }
            button("Show client ID") {
                preferences = Preferences(context)
                onClick {
                    alert(title = "Client ID",
                            message = "${preferences!!.clientID}") {
                        positiveButton("Close") { //this@alert.dismiss()
                            }
                    }.show()
                }
            }
            button("Show token") {
                preferences = Preferences(context)
                onClick {
                    alert(title = "Token",
                            message = "${preferences!!.clientToken}") {
                        positiveButton("Close") { //this@alert.dismiss()
                        }
                    }.show()
                }
            }
        }
    }
}
