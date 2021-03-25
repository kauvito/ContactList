package com.kauvito.contactlist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Classe modelo
@Parcelize
 data class Contacts(
    var name: String,
    var phone: String,
    var photograph: String = ""
):Parcelable