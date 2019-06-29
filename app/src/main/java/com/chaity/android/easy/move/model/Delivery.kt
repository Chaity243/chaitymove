package com.chaity.android.easy.move.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Class which provides a model for Delivery
 * @constructor Sets all properties of the post
 * @property id the unique identifier of the delivery item
 * @property description the description details of the delivery item
 * @property location location of delivery item
 */
@Entity

data class Delivery (
        @SerializedName("id")     @field:PrimaryKey  val id: Int,
        @SerializedName("description")      val description: String,
        @SerializedName("imageUrl")    val imageUrl: String,
        @SerializedName("location")  val location: Location

): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Location::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeParcelable(location,flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Delivery> {
        override fun createFromParcel(parcel: Parcel): Delivery {
            return Delivery(parcel)
        }

        override fun newArray(size: Int): Array<Delivery?> {
            return arrayOfNulls(size)
        }
    }
}