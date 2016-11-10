// MyAidl.aidl
package ready.mumu.service;

import  ready.mumu.service.myParcelable;

// Declare any non-default types here with import statements

interface MyAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

     int addnum(int num1 , int num2);

     String readText(in myParcelable par);
}
