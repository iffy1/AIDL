// IMyAidlInterface.aidl
package com.example.myapplication;
import com.example.myapplication.Student;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
        boolean getService(String name);
        Student getStudent();
        void SendStudent(out Student s);

        //inouttest
        Student addStudentIn(in Student s);
        Student addStudentOut(out Student s);
        Student addStudentInOut(inout Student s);
}

