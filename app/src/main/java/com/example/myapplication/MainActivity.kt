package com.example.myapplication

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.IMyAidlInterface.Stub.getDefaultImpl

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ServiceConnection {
    override fun onServiceDisconnected(name: ComponentName?) {
        println("onServiceDisconnected")
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        println("onServiceConnected")
        //直接操作Binder
        val _data = android.os.Parcel.obtain()
        val _reply = android.os.Parcel.obtain()
        val _result: Boolean
        try {
            _data.writeInterfaceToken("com.example.myapplication.IMyAidlInterface")
            _data.writeString("iffy1")
            val _status =
                service!!.transact(IMyAidlInterface.Stub.TRANSACTION_getService, _data, _reply, 0)
            _reply.readException()
            _result = 0 != _reply.readInt()
        } finally {
            _reply.recycle()
            _data.recycle()
        }

        if (_result) {
            println("找到了iffy1")
        } else {
            println("没找到iffy1")
        }


//        val mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)//Binder转化为 Interface(Stub.proxy)
//        println("得到类 $mIMyAidlInterface")
//        if (mIMyAidlInterface.getService("iffy")) {
//            println("找到了iffy")
//        } else {
//            println("没找到iffy")
//        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val i = Intent("com.example.iffyservice.Iffyservice")
            i.setPackage("com.example.iffyservice")
            if (bindService(i, this, Service.BIND_AUTO_CREATE)) {
                println("绑定成功")
            } else {
                println("绑定失败")
            }


        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
