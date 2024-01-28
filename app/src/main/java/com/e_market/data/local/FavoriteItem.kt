package com.e_market.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "favorite_items")
data class FavoriteItem(
    var price: Float,
    var model: String,
    var brand: String,
    var imageURL: String,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int
) : Serializable