package com.example.myapplication

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.IMyAidlInterface.Stub.getDefaultImpl

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ServiceConnection {
    var mbinder: IBinder? = null
    lateinit var mIMyAidlInterface: IMyAidlInterface
    override fun onServiceDisconnected(name: ComponentName?) {
        println("onServiceDisconnected")
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        mbinder = service
        println("onServiceConnected")
        //直接操作Binder
//        val _data = android.os.Parcel.obtain()
//        val _reply = android.os.Parcel.obtain()
//        val _result: Boolean
//        try {
//            _data.writeInterfaceToken("com.example.myapplication.IMyAidlInterface")
//            _data.writeString("iffy1")
//            val _status =
//                service!!.transact(IMyAidlInterface.Stub.TRANSACTION_getService, _data, _reply, 0)
//            _reply.readException()
//            _result = 0 != _reply.readInt()
//        } finally {
//            _reply.recycle()
//            _data.recycle()
//        }
//
//        if (_result) {
//            println("找到了iffy1")
//        } else {
//            println("没找到iffy1")
//        }

//常规用法
        mIMyAidlInterface =
            IMyAidlInterface.Stub.asInterface(service)//Binder转化为 Interface(Stub.proxy)
        println("得到类 $mIMyAidlInterface")
        if (mIMyAidlInterface.getService("iffy")) {
            println("找到了iffy")
        } else {
            println("没找到iffy")
        }

        //死亡代理用来监听Server端状态
        //adb shell am force-stop com.example.iffyservice 来验证
        service?.linkToDeath(object : IBinder.DeathRecipient {
            override fun binderDied() {
                println("那位挂了")
                mIMyAidlInterface.asBinder().unlinkToDeath(this, 0)
               // bindService()
            }
        }, 0)

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            bindService()
        }
        //kotlin-android-extensions 可以直接操作控件id
        talk_to_service.setOnClickListener {
            if (mIMyAidlInterface.getService("ttt")) {
                println("找到了ttt")
            } else {
                println("没找到ttt")
            }

        }
        send_student_to_service.setOnClickListener {
            println("点击发送学生")
            mIMyAidlInterface.SendStudent(Student("iffy client",22))

        }

        get_student_to_service.setOnClickListener {
            println("点击发送学生")
           println("得到学生 ${mIMyAidlInterface.student.nane} ${mIMyAidlInterface.student.age}")
        }

        add_studentIn.setOnClickListener {
            var sIn = Student("StudentIn client",201)
            println("add_studentIn ${sIn.nane} ${sIn.age} ")
            mIMyAidlInterface.addStudentIn(sIn)
            println("add_studentIn Server返回 ${sIn.nane} ${sIn.age} ")
        }

        add_studentOut.setOnClickListener {
            var sIn = Student("StudentOut client",202)
            println("add_studentOut ${sIn.nane} ${sIn.age} ")
            mIMyAidlInterface.addStudentOut(sIn)
            println("Server改变了client端对象的参数 ${sIn.nane} ${sIn.age} ")
        }

        add_studentInOut.setOnClickListener {
            var sIn = Student("StudentInOut client",203)
            println("add_studentInOut ${sIn.nane} ${sIn.age} ")
            mIMyAidlInterface.addStudentInOut(sIn)
            println("Server改变了client端对象的参数 ${sIn.nane} ${sIn.age} ")
        }


    }

    fun bindService() {
        val i = Intent("com.example.iffyservice.Iffyservice")
        i.setPackage("com.example.iffyservice")
        if (bindService(i, this, Service.BIND_AUTO_CREATE)) {
            println("绑定成功")
        } else {
            println("绑定失败")
        }
    }

}
