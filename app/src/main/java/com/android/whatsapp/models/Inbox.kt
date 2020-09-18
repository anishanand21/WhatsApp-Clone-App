package com.android.whatsapp.models

import java.util.*

data class Inbox(
    val msg: String,
    val from: String,
    var name: String,
    var image: String,
    val time: Date = Date(),
    var count: Int
) {
    constructor():this("","","","",Date(),0)
}