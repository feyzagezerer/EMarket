package com.e_market.data.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductResponse(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("id")
    val id: String,
): Parcelable