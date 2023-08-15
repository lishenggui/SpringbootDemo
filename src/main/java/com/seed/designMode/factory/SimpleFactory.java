package com.seed.designMode.factory;

public class SimpleFactory {
    public static void main(String[] args) {
        /**
         * simpleFactory
         */
        SimpleFactory.makeProduct("A").doSomeThing();
        /**
         * FactoryMethod
         */
        IFactory iFactory = new FactoryA();
        iFactory.makeProduct().doSomeThing();
    }

    public  static  IProduct makeProduct(String type){
        if("A".equals(type)){
            return new ProductA();
        }
        if("B".equals(type)){
            return new ProductB();
        }
        return  null;
    }

    /**
     * 升级版，不需要传类型
     * @param clazz
     * @return
     */
    public  static  IProduct create(Class<? extends IProduct> clazz) throws IllegalAccessException, InstantiationException {
        if(clazz!=null){
            return clazz.newInstance();
        }
        return  null;
    }

}
