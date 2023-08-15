package com.seed.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<k,v> extends LinkedHashMap<k,v> {

    private  final int maxSize;

    public LRUCache(int initCap,int maxSize) {
        super(initCap,0.75f,true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<k, v> eldest) {
        return size()>maxSize;
    }

    public static void main(String[] args) {
        LRUCache<String,String>cache = new LRUCache<>(4,3);
        cache.put("a","a");
        cache.put("b","a");
        cache.get("a");
        cache.put("c","a");
        cache.put("d","a");
        cache.get("c");
        System.out.println(cache);
    }
}
