package com.seed.designMode.Proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDeepProxy implements MethodInterceptor {

    <T extends TrainStation> TrainStation getProxy(Class<T> targetInstanceClazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetInstanceClazz);
        enhancer.setCallback(new CglibDeepProxy());
        return  (TrainStation)enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代售点加收5%手续费！");
        return methodProxy.invokeSuper(o,objects);
    }
    public static void main(String[] args) {
        CglibDeepProxy staticProxy = new CglibDeepProxy();
        staticProxy.getProxy(GuangzhouTranStation.class).sellTickets();
        staticProxy.getProxy(BeijingTranStation.class).sellTickets();
    }
}
