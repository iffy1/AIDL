package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

class Student(var nane: String?, var age: Int) : Parcelable {
    //自己实现
    constructor() : this("", 1)

    override fun describeContents(): Int {
        return 0
    }

    /**反序列化*/
    constructor(parcel: Parcel) : this() {
        nane = parcel.readString()
        age = parcel.readInt()
    }

    /**序列化*/
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //序列化和反序列化读写顺序要一致
        parcel.writeString(nane)
        parcel.writeInt(age)
    }

    /**反序列化*/
    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }


}