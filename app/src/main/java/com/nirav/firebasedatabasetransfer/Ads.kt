package com.nirav.firebasedatabasetransfer

import com.google.firebase.database.PropertyName

data class Ads(
    @PropertyName("isVisible")
    var isVisible: Boolean = true
)
