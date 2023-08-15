package com.seed.designMode.singleton;

public class DoubleCheckSingleton {
    private volatile  static DoubleCheckSingleton doubleCheckSingleton = null;

    private DoubleCheckSingleton(){

    }

    public  static DoubleCheckSingleton getInstance(){
        if(doubleCheckSingleton ==null){
            synchronized (DoubleCheckSingleton.class){
                if(doubleCheckSingleton ==null){
                    doubleCheckSingleton = new DoubleCheckSingleton();
                }
            }
        }
        return doubleCheckSingleton;
    }

}
