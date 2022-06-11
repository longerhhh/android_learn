package com.example.test.tst;

public class A {
    int a;

    public A() {
        System.out.println("A");
    }

    {
        a = 0;
        System.out.println("A:init");
    }

    public static void main(String[] args) {
        new B();
    }


    static class B extends A {
        public B() {
            super();
            System.out.println("B");
        }

        {
            System.out.println("B:init");
        }
    }

    static class C extends B {
        int c;

        public C() {
            System.out.println("C");
        }

        public C(int cc) {
            this();
            this.c = cc;
        }

        {
//        this.c = cc;
        }
    }
}