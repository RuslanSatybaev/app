package com.edu.ulab.app.my_test;

public class DaughterSuper extends SuperExample {
    public static String ci = "er";
    public DaughterSuper(String city) {
        super(city);
    }

    public static void main(String[] args) {
        System.out.println(new DaughterSuper(ci).name());
    }


//    public  String name() {
//        return super.name();
//    }
}
