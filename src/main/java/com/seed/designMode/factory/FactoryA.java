package com.seed.designMode.factory;

public class FactoryA implements  IFactory{
    @Override
    public IProduct makeProduct() {
        return new ProductA();
    }
}
