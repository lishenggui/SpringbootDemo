package com.seed.designMode.Proxy;

public class BeijingTranStation implements TrainStation {
    @Override
    public void sellTickets() {
        System.out.println("Beijing站卖出票拉！！");
    }
}
