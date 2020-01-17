// IMyAidlInterface.aidl
package com.example.myapplication;
import com.example.myapplication.Student;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
        boolean getService(String name);
        Student getStudent();
        void SendStudent(out Student s);

        //inout测试

        //in 代表C->S S对Student的修改不会作用到C端的实例
        void addStudentIn(in Student s);

        //out 代表S->C C端的内容不能传递到S端，S端会新建一个student并且赋值给C端的student
        void addStudentOut(out Student s);

        //inout双向传递
        void addStudentInOut(inout Student s);
}

