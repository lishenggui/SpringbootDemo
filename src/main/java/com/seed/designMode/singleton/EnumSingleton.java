package com.seed.designMode.singleton;

public class EnumSingleton {
    private enum InEnum{
        INSTANCE;
        private  EnumSingleton enumSingleton ;
        InEnum(){
            enumSingleton = new EnumSingleton();
        }

        public  EnumSingleton  getInstance(){
            return  enumSingleton;
        }

    }
    private EnumSingleton(){

    }
    public  static EnumSingleton getInstance(){
        return  InEnum.INSTANCE.getInstance();
    }
}
