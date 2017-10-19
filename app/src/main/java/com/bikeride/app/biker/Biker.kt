package com.bikeride.app.biker

import android.app.Activity
import android.os.Bundle
import com.bikeride.app.utils.Preferences
import com.github.kittinunf.fuel.Fuel
import com.bikeride.app.api.Biker as BikerAPI
import com.bikeride.app.api.data.Biker as BikerData
import org.jetbrains.anko.alert
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class Biker : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            val context = this.context
            textView("Biker")
            com.bikeride.app.api.Biker.defaults(context)
            val preferences: Preferences? = Preferences(context)
            Fuel.get("/api/biker/" + preferences!!.bikerID).responseObject(com.bikeride.app.api.data.Biker.Deserializer()) { req, res, result ->
                val (bikerRsp, err) = result
                textView("ID:"+bikerRsp?.bikerID?.bikerID)
                textView("Name:"+bikerRsp?.bikerFields?.name)
                textView("Avatar B64:"+bikerRsp?.bikerFields?.avatarb64)
                textView("Blood Type:"+bikerRsp?.bikerFields?.bloodType)
                textView("Mobile:"+bikerRsp?.bikerFields?.mobile)
                textView("Email:"+bikerRsp?.bikerFields?.email)
                textView("Active:"+bikerRsp?.bikerFields?.active)
            }
        }
    }
}

