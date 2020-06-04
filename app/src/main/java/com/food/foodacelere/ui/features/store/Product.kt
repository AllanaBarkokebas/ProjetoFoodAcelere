package com.food.foodacelere.ui.features.store

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(var title: String, var price: String, var image: String) : Parcelable