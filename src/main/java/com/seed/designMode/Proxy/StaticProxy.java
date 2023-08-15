package com.seed.designMode.Proxy;

public class StaticProxy  implements   TrainStation{

    GuangzhouTranStation guangzhouTranStation = new GuangzhouTranStation();
    @Override
    public void sellTickets() {
        System.out.println("代售点加收5%手续费！");
        guangzhouTranStation.sellTickets();
    }

    public static void main(String[] args) {
        StaticProxy staticProxy = new StaticProxy();
        staticProxy.sellTickets();
    }
}
