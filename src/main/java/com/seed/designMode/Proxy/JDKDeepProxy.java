package com.seed.designMode.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDeepProxy implements InvocationHandler {
    TrainStation trainStation ;
    TrainStation getProxy( TrainStation trainStation){
        this.trainStation = trainStation;
        Class<? extends TrainStation>clazz = trainStation.getClass();
        return (TrainStation) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代售点加收5%手续费！");
        return method.invoke(trainStation,args);
    }
    public static void main(String[] args) {
        JDKDeepProxy staticProxy = new JDKDeepProxy();
        staticProxy.getProxy(new GuangzhouTranStation()).sellTickets();
        staticProxy.getProxy(new BeijingTranStation()).sellTickets();
    }

}
