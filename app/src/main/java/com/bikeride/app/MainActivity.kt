package com.bikeride.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bikeride.app.biker.Biker
import org.jetbrains.anko.*
import com.bikeride.app.utils.Preferences
import com.bikeride.app.authentication.Authentication
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var preferences: Preferences? = null

        val authentication = Intent(this, Authentication::class.java)
        val biker = Intent(this, Biker::class.java)

        verticalLayout {
            preferences = Preferences(this.context)
            if (preferences!!.bikerID=="INVALID"){

                startActivity(intent)
            }
            textView("Main activity")
            button("Authentication") {
                onClick { startActivity(authentication) }
            }
            button("Biker") {
                onClick { startActivity(biker) }
            }
        }
    }
}
