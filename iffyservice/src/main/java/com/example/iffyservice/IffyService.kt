package com.example.iffyservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.myapplication.IMyAidlInterface
import com.example.myapplication.Student

class IffyService : Service() {
    //Stub类
    val binder = object : IMyAidlInterface.Stub() {
        override fun addStudentIn(s: Student?): Student {
            println("addStudentIn 我收到学生了${s?.nane} ${s?.age}")
            return Student("from Service In",100)
        }

        override fun addStudentOut(s: Student?): Student {
            println("addStudentOut 我收到学生了${s?.nane} ${s?.age}")
            return Student("from Service Out",101)
        }

        override fun addStudentInOut(s: Student?): Student {
            println("addStudentInOut 我收到学生了${s?.nane} ${s?.age}")
            return Student("from Service InOut",102)
        }

        override fun SendStudent(s: Student?) {
            println("我收到学生了${s?.nane} ${s?.age}")
        }

        override fun getStudent(): Student {
            return Student("student form service", 99)
        }

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