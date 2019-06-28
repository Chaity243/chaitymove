package com.chaity.android.easy.move.model

import android.os.Parcel
import android.os.Parcelable
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

    companion object CREATOR : Parcelable.Creator<Deliveries> {
        override fun createFromParcel(parcel: Parcel): Deliveries {
            return Deliveries(parcel)
        }

        override fun newArray(size: Int): Array<Deliveries?> {
            return arrayOfNulls(size)
        }
    }
}