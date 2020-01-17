package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

class Car(var color:Int,var brand:String?) :Parcelable{

    //序列化
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(color)
        parcel.writeString(brand)
    }

    override fun describeContents(): Int {
        return 0
    }

    //被反序列化的Creator 调用
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    )

    //反序列化
    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }
        //实现序列化的类 这个类的Array也支持序列化
        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }
}