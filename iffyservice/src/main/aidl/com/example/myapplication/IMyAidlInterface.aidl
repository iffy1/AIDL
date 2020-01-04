// IMyAidlInterface.aidl
package com.example.myapplication;
import com.example.myapplication.Student;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
        boolean getService(String name);
        Student getStudent();
        void SendStudent(out Student s);

        //inouttest
        void addStudentIn(in Student s);
        void addStudentOut(out Student s);
        void addStudentInOut(inout Student s);
}

