package com.example.hospital.Data.Models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.WriteWith

data class ModelHistory(
    val data: List<DataHistory>
)

data class DataHistory(
    val clinic: Clinic,
    val number_in_qeue: Int,
    val payment: String,
    val id: Int,
    val payment_status: Boolean,
    val price: Double,
    val reserved_at: String,
    val status: String,
    val working_hour: WorkingHour
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Clinic::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(), // Read the id property
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(WorkingHour::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(clinic, flags)
        parcel.writeInt(number_in_qeue)
        parcel.writeString(payment)
        parcel.writeInt(id) // Write the id property
        parcel.writeByte(if (payment_status) 1 else 0)
        parcel.writeDouble(price)
        parcel.writeString(reserved_at)
        parcel.writeString(status)
        parcel.writeParcelable(working_hour, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataHistory> {
        override fun createFromParcel(parcel: Parcel): DataHistory {
            return DataHistory(parcel)
        }

        override fun newArray(size: Int): Array<DataHistory?> {
            return arrayOfNulls(size)
        }
    }
}

data class Clinic(
    val id: Int,
    val name: String,
    val price: Double,
    val status: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Clinic> {
        override fun createFromParcel(parcel: Parcel): Clinic {
            return Clinic(parcel)
        }

        override fun newArray(size: Int): Array<Clinic?> {
            return arrayOfNulls(size)
        }
    }
}

data class WorkingHour(
    val day: String,
    val day_name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(day)
        parcel.writeString(day_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WorkingHour> {
        override fun createFromParcel(parcel: Parcel): WorkingHour {
            return WorkingHour(parcel)
        }

        override fun newArray(size: Int): Array<WorkingHour?> {
            return arrayOfNulls(size)
        }
    }
}