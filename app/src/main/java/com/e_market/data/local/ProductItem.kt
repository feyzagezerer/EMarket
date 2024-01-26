package com.e_market.data.local


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product_items")
data class ProductItem(
    var name: String,
    var amount: Int,
    var price: Float,
    var model: String,
    var brand: String,
    @PrimaryKey(autoGenerate = true)
    var id: String
): Parcelable