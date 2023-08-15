package com.seed.designMode.Adapter;

public class PropertyAdapter implements DC5V {
    private  AC220V ac220V= new AC220V();
    //适配器Adapter
    public int output5V() {
        int out = ac220V.output220V();
        System.out.println("220V适配转换成5V");
        return out / 44;
    }

    //测试
    public static void main(String[] args) {
        DC5V dc5v = new PropertyAdapter();
        int a = dc5v.output5V();
        System.out.println(a);
    }
}
