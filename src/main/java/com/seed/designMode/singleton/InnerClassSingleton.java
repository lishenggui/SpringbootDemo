package com.seed.designMode.singleton;

public class InnerClassSingleton {
    private static class InClass{
        private  final  static InnerClassSingleton singleton = new InnerClassSingleton();

    }
    private InnerClassSingleton(){

    }
    public  static InnerClassSingleton getInstance(){
        return  InClass.singleton;

    }
}
