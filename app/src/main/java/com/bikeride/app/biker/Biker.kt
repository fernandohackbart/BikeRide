package com.bikeride.app.biker

import android.app.Activity
import android.os.Bundle
import com.bikeride.app.api.APIDefaults
import com.bikeride.app.api.data.BikerChangeFields
import com.bikeride.app.api.data.BikerID
import com.bikeride.app.api.data.GeneratePINResponse
import com.bikeride.app.utils.Preferences
import com.github.kittinunf.fuel.Fuel
import com.google.gson.GsonBuilder
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import com.bikeride.app.api.data.Biker as BikerData

class Biker : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            val preferences: Preferences? = Preferences(this.context)
            val gson = GsonBuilder().setPrettyPrinting().create()
            val bikerID = preferences!!.bikerID
            var biker: BikerData? = null
            APIDefaults.defaults(context)

            textView("Biker") {
                textSize = 19f
            }

            Fuel.get("/api/biker/"+bikerID).responseObject(BikerData.Deserializer()) { req, res, result ->
            val (bikerRsp, err) = result
                biker = bikerRsp
                if(err!=null)
                {
                    alert(title = "Error",
                            message = err.message.orEmpty()) {
                        positiveButton("Close") {
                        }
                    }.show()
                }

                textView("ID:"+biker?.bikerID?.bikerID)

                linearLayout{
                    textView("Name:")
                    val name = editText(biker?.bikerFields?.name)
                    button("save") {
                        onClick {
                            Fuel.put("/api/biker/"+bikerID+"/name").body(gson.toJson(BikerChangeFields(name=name.text.toString()))).responseObject(GeneratePINResponse.Deserializer()) { req, res, result ->
                                val (_ , err) = result
                                if(err!=null)
                                {
                                    alert(title = "Error",
                                            message = err.message.orEmpty()) {
                                        positiveButton("Close") {
                                        }
                                    }.show()
                                }else{
                                    toast("Name saved")
                                }
                            }
                        }
                    }
                }

                linearLayout{
                    textView("Blood Type:")
                    val bloodtype = editText(biker?.bikerFields?.bloodType)
                    button("save") {
                        onClick {
                            Fuel.put("/api/biker/"+bikerID+"/bloodtype").body(gson.toJson(BikerChangeFields(bloodType= bloodtype.text.toString()))).responseObject(GeneratePINResponse.Deserializer()) { req, res, result ->
                                val (_ , err) = result
                                if (err != null) {
                                    alert(title = "Error",
                                            message = err.message.orEmpty()) {
                                        positiveButton("Close") {
                                        }
                                    }.show()
                                } else {
                                    toast("Blood type saved")
                                }
                            }
                        }
                    }
                }

                linearLayout{
                    textView("Mobile:")
                    val mobile = editText(biker?.bikerFields?.mobile)
                    button("save") {
                        onClick {
                            Fuel.put("/api/biker/"+bikerID+"/mobile").body(gson.toJson(BikerChangeFields(mobile= mobile.text.toString()))).responseObject(GeneratePINResponse.Deserializer()) { req, res, result ->
                                val (_ , err) = result
                                if (err != null) {
                                    alert(title = "Error",
                                            message = err.message.orEmpty()) {
                                        positiveButton("Close") {
                                        }
                                    }.show()
                                } else {
                                    toast("Mobile saved")
                                }
                            }
                        }
                    }
                }

                linearLayout{
                    textView("Email:")
                    val email = editText(biker?.bikerFields?.email)
                    button("save") {
                        onClick {
                            Fuel.put("/api/biker/"+bikerID+"/email").body(gson.toJson(BikerChangeFields(email= email.text.toString()))).responseObject(GeneratePINResponse.Deserializer()) { req, res, result ->
                                val (_ , err) = result
                                if (err != null) {
                                    alert(title = "Error",
                                            message = err.message.orEmpty()) {
                                        positiveButton("Close") {
                                        }
                                    }.show()
                                } else {
                                    toast("Email saved")
                                }
                            }
                        }
                    }
                }

                linearLayout{
                    textView("Active:")
                    switch{
                        isChecked = biker!!.bikerFields!!.active.or(false)

                        onClick {
                            if(isChecked){
                                Fuel.post("/api/biker/"+bikerID+"/activate").responseObject(BikerID.Deserializer()) { req, res, result ->
                                    val (_ , err) = result
                                    if (err != null) {
                                        alert(title = "Error",
                                                message = err.message.orEmpty()) {
                                            positiveButton("Close") {
                                            }
                                        }.show()
                                    } else {
                                        toast("Biker activated")
                                    }
                                }
                            }else{
                                Fuel.post("/api/biker/"+bikerID+"/deactivate").responseObject(BikerID.Deserializer()) { req, res, result ->
                                    val (_ , err) = result
                                    if (err != null) {
                                        alert(title = "Error",
                                                message = err.message.orEmpty()) {
                                            positiveButton("Close") {
                                            }
                                        }.show()
                                    } else {
                                        toast("Biker deactivated")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

