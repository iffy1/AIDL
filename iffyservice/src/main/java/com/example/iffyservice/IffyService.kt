package com.example.iffyservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.myapplication.IMyAidlInterface

class IffyService : Service() {
    //Stub类
    val binder = object : IMyAidlInterface.Stub() {
        override fun getService(name: String?): Boolean {
            return "iffy" == name
        }
    }


    //Service
    override fun onBind(intent: Intent?): IBinder? {
        //返回Stub(Binder)
        return binder
    }
}