package com.seed.designMode.Adapter;

public class ClassAdapter extends AC220V implements DC5V {
    //适配器Adapter
    public int output5V() {
        int out = super.output220V();
        return out / 44;
    }

    //测试
    public static void main(String[] args) {
        DC5V dc5v = new ClassAdapter();
        int a = dc5v.output5V();
        System.out.println(a);
    }
}
