package com.seed.designMode.factory;

public class FactoryB implements  IFactory{
    @Override
    public IProduct makeProduct() {
        return new ProductB();
    }
}
