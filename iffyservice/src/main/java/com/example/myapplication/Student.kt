package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

class Student(var nane: String?,var age: Int) : Parcelable {
    //自己实现
    constructor():this("",1)

    constructor(parcel: Parcel) : this() {
        nane = parcel.readString()
        age = parcel.readInt()
    }

    //自己实现
    fun readFromParcel(parcel: Parcel){
        nane = parcel.readString()
        age = parcel.readInt()
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nane)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }


}