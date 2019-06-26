package com.chaity.android.easy.move.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Class which provides a model for Deliveries
 * @constructor Sets all properties of the post
 * @property id the unique identifier of the deliveries
 * @property description the description details of the deliveries
 * @property location location of deliveries
 */
@Entity
data class Deliveries (
        @SerializedName("id")     @field:PrimaryKey  val id: Int,
        @SerializedName("description")      val description: String,
        @SerializedName("imageUrl")    val imageUrl: String,
        @SerializedName("location")   val location: Location

)